package com.robolucha.runner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.robolucha.game.action.AddOnListenEventAction;
import com.robolucha.game.action.ChangeStateAction;
import com.robolucha.game.action.CheckRadarAction;
import com.robolucha.game.action.CheckRespawnAction;
import com.robolucha.game.action.GameAction;
import com.robolucha.game.action.ReduceCoolDownAction;
import com.robolucha.game.action.RemoveDeadAction;
import com.robolucha.game.action.RepeatAction;
import com.robolucha.game.action.TriggerEventsAction;
import com.robolucha.game.event.LuchadorEvent;
import com.robolucha.game.event.LuchadorEventListener;
import com.robolucha.game.event.MatchEventListener;
import com.robolucha.game.event.OnHitOtherEvent;
import com.robolucha.game.event.OnHitWallEvent;
import com.robolucha.game.processor.BulletsProcessor;
import com.robolucha.game.processor.IRespawnProcessor;
import com.robolucha.game.processor.PunchesProcessor;
import com.robolucha.game.processor.RespawnProcessorFactory;
import com.robolucha.game.vo.MatchInitVO;
import com.robolucha.game.vo.MatchReadyToStartTeamInformationVO;
import com.robolucha.game.vo.MatchReadyToStartVO;
import com.robolucha.game.vo.MessageVO;
import com.robolucha.models.Bullet;
import com.robolucha.monitor.ServerMonitor;
import com.robolucha.monitor.ThreadStatus;
import com.robolucha.publisher.MatchStatePublisher;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.runner.luchador.LutchadorRunnerCreator;
import com.robolucha.shared.Calc;
import com.robolucha.shared.JSONFormat;

import io.reactivex.subjects.PublishSubject;
import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelGameDefinition;
import io.swagger.client.model.ModelMatch;
import io.swagger.client.model.ModelMatchMetric;
import io.swagger.client.model.ModelSceneComponent;
import io.swagger.client.model.ModelTeam;
import io.swagger.client.model.ModelTeamDefinition;

/**
 * main match logic
 */
public class MatchRunner implements Runnable, ThreadStatus {

	private static final long DEEP_BREATH_SLEEP = 3000;
	private static final String WALL_TYPE = "wall";

	private SafeList bullets;
	private SafeList punches;

	private List<GameAction> runOnActive;
	private PunchesProcessor punchesProcessor;
	private BulletsProcessor bulletsProcessor;
	private double delta;

	private PublishSubject<ModelMatch> onMatchStart;
	private PublishSubject<ModelMatch> onMatchEnd;
	private PublishSubject<String> onRunnerShutdown;
	private PublishSubject<MessageVO> onMessage;
	private PublishSubject<MatchInitVO> onInit;
	private PublishSubject<MatchReadyToStartVO> onCheckingReadiness;

	private List<MatchRunnerListener> listeners;
	private ModelGameDefinition gameDefinition;
	private MatchStatePublisher publisher;

	// TODO: replace all the listeners by Subjects
	private List<LuchadorEventListener> eventListeners;
	private List<MatchEventListener> matchEventListeners;

	static Logger logger = Logger.getLogger(MatchRunner.class);

	LinkedHashMap<Integer, LuchadorRunner> runners;

	boolean alive;
	private IRespawnProcessor respawnProcessor;

	private String status;
	private String threadName;
	private Long startTime;
	private long timeElapsed;
	private boolean cleanupActive = true;

	private MatchEventHandler eventHandler;
	private LutchadorRunnerCreator luchadorCreator;
	SceneComponentEventsRunner eventsRunner;
	private ModelMatch match;
	private MatchRunnerMonitor monitor;
	private IMatchRunnerAPI api;

	public MatchEventHandler getEventHandler() {
		return eventHandler;
	}

	public MatchRunner(ModelGameDefinition gameDefinition, ModelMatch match, RemoteQueue queue,
			ServerMonitor serverMonitor, IMatchRunnerAPI api) throws Exception {
		logger.info("CREATE MatchRunner: " + this + " gameDefinition: " + JSONFormat.clean(gameDefinition.toString()));

		threadName = String.format("MatchRunner-Thread-%s-%s", gameDefinition.getName(), match.getId());
		status = ThreadStatus.STARTING;
		alive = true;
		delta = 0.0;
		this.gameDefinition = gameDefinition;
		this.match = match;
		this.api = api;

		ModelMatchMetric metric = new ModelMatchMetric();
		metric.setGameDefinitionID(gameDefinition.getId());
		metric.setMatchID(match.getId());
		monitor = new MatchRunnerMonitor(serverMonitor, metric, Config.getInstance().getMonitorInterval());

		listeners = new LinkedList<MatchRunnerListener>();

		runOnActive = new LinkedList<GameAction>();
		runOnActive.add(new RepeatAction());
		runOnActive.add(new CheckRadarAction(this));
		runOnActive.add(new TriggerEventsAction());
		runOnActive.add(new ChangeStateAction());

		runners = new LinkedHashMap<Integer, LuchadorRunner>();

		eventsRunner = new SceneComponentEventsRunner(this);
		respawnProcessor = RespawnProcessorFactory.get(this);

		bullets = new SafeList(new LinkedList<Bullet>());
		punches = new SafeList(new LinkedList<Punch>());

		eventListeners = Collections.synchronizedList(new LinkedList<LuchadorEventListener>());
		matchEventListeners = Collections.synchronizedList(new LinkedList<MatchEventListener>());

		punchesProcessor = new PunchesProcessor(this, punches);
		bulletsProcessor = new BulletsProcessor(this, bullets);

		eventHandler = new MatchEventHandler(this, threadName, serverMonitor);
		luchadorCreator = new LutchadorRunnerCreator(this, queue, serverMonitor);

		onMatchStart = PublishSubject.create();
		onMatchEnd = PublishSubject.create();
		onRunnerShutdown = PublishSubject.create();
		onMessage = PublishSubject.create();
		onInit = PublishSubject.create();
		onCheckingReadiness = PublishSubject.create();

		logger.info("MatchRunner created:" + this);
	}
	
	public boolean isFriendlyFire(){
		if (gameDefinition.getTeamDefinition() != null) {
			return gameDefinition.getTeamDefinition().isFriendlyFire();
		}
		return true;
	}

	public List<ModelSceneComponent> getSceneComponents() {
		return gameDefinition.getSceneComponents();
	}

	public ModelMatch getMatch() {
		return match;
	}

	public void addListener(MatchRunnerListener listener) {
		listeners.add(listener);
		listener.subscribe(this);
	}

	public PublishSubject<LuchadorRunner> add(final ModelGameComponent component) throws Exception {
		return add(component, 0);
	}

	public PublishSubject<LuchadorRunner> add(final ModelGameComponent component, Integer teamId) throws Exception {

		if (runners.containsKey(component.getId())) {
			String message = "Trying to add luchador that is already in the match, id: " + component.getId();
			logger.info(message);

			PublishSubject<LuchadorRunner> result = PublishSubject.create();
			result.onError(new Exception(message));
			result.onComplete();
			return result;
		}

		if (getNumberOfPlayers() >= gameDefinition.getMaxParticipants()) {
			String message = "Trying to add luchador beyond the limit";
			logger.info(message);

			PublishSubject<LuchadorRunner> result = PublishSubject.create();
			result.onError(new Exception(message));
			result.onComplete();
			return result;
		}

		// Check limits of team
		if (gameDefinition.getTeamDefinition() != null) {
			ModelTeamDefinition teamDefinition = gameDefinition.getTeamDefinition();
			if (teamDefinition != null && teamDefinition.getTeams() != null) {

				Optional<ModelTeam> find = teamDefinition.getTeams().stream().filter(t -> t.getId() == teamId)
						.findFirst();

				if (find.isPresent()) {
					ModelTeam team = find.get();
					List<LuchadorRunner> participants = getRunnersByTeam(teamId);
					if (participants.size() + 1 > team.getMaxParticipants()) {

						String message = "Team max participants is reached";
						logger.info(message);

						PublishSubject<LuchadorRunner> result = PublishSubject.create();
						result.onError(new Exception(message));
						result.onComplete();
						return result;
					}

				}
			}
		}

		logger.info("new luchador added to the match: " + JSONFormat.clean(component.toString()));
		component.setIsNPC(false);
		monitor.addPlayer();

		return luchadorCreator.add(component, teamId);
	}
	
	private List<LuchadorRunner> getRunnersByTeam(int teamId) {
		List<LuchadorRunner> result = runners.values().stream().filter(r -> r.getTeamId() == teamId)
				.collect(Collectors.toList());
		return result;
	}

	private int getNumberOfPlayers() {
		int counter = 0;
		LuchadorRunner[] localRunners = new LuchadorRunner[runners.values().size()];
		localRunners = runners.values().toArray(localRunners);

		for (int i = 0; i < localRunners.length; i++) {
			LuchadorRunner runner = localRunners[i];
			if (!runner.getGameComponent().isIsNPC()) {
				counter++;
			}
		}
		return counter;
	}

	public PublishSubject<LuchadorRunner> addNPC(final ModelGameComponent component) throws Exception {
		logger.info("new NPC added to the match: " + JSONFormat.clean(component.toString()));
		component.setIsNPC(true);
		return luchadorCreator.add(component, 0);
	}

	public PublishSubject<LuchadorRunner> addLuchador(final ModelGameComponent component, Integer teamId)
			throws Exception {
		return add(component, teamId);
	}

	public void fire(Bullet bullet) {

		if (logger.isDebugEnabled()) {
			logger.debug("-- FIRE ! = " + bullet);
		}

		bullets.add(bullet);
	}

	public void punch(LuchadorRunner lutchadorRunner, double amount, double x, double y, double angle) {
		Punch punch = new Punch(lutchadorRunner, amount, x, y, angle);
		punches.add(punch);
	}

	public void run() {
		Thread.currentThread().setName(threadName);

		startTime = System.currentTimeMillis();
		this.onInit.onNext(new MatchInitVO(startTime));
		this.onInit.onComplete();

		// TODO: add observable to the init procedures
		// getOnInit()... is it completed?

		mainLoop();
	}

	MatchReadyToStartVO readyToStartMatch() {
		
		MatchReadyToStartVO readyToStart = populateReadyToStart();
		
		if (readyToStart.participants < readyToStart.minParticipants) {
			readyToStart.ready = false;
			return readyToStart;
		}
		
		for (MatchReadyToStartTeamInformationVO info : readyToStart.teamParticipants) {
			if (info.participants < info.minParticipants ) {
				logger.info(String.format("Match: %s, Missing participants to start on team: %s, expected: %s, current: %s",
						match.getId(), info.teamID, info.minParticipants, info.participants));
				readyToStart.ready = false;
				return readyToStart;
			}
		}
		
		return readyToStart;
	}

	private MatchReadyToStartVO populateReadyToStart() {
		
		MatchReadyToStartVO readyToStart = new MatchReadyToStartVO();
		readyToStart.ready = true;
		readyToStart.matchID = match.getId();
		readyToStart.minParticipants = gameDefinition.getMinParticipants();
		readyToStart.maxParticipants = gameDefinition.getMaxParticipants();
		readyToStart.participants = getNumberOfPlayers();
		readyToStart.teamParticipants = new MatchReadyToStartTeamInformationVO[0]; 

		List<MatchReadyToStartTeamInformationVO> teamInfo = new ArrayList<MatchReadyToStartTeamInformationVO>();

		ModelTeamDefinition teamDefinition = gameDefinition.getTeamDefinition();
		if (teamDefinition != null && teamDefinition.getTeams() != null && teamDefinition.getTeams().size() > 0) {

			// count participants by team.
			Map<Integer, Integer> countbyTeam = new LinkedHashMap<Integer, Integer>();
			runners.values().forEach((runner) -> {
				int teamId = runner.getTeamId();

				if (countbyTeam.containsKey(teamId)) {
					int value = countbyTeam.get(teamId);
					countbyTeam.put(teamId, value + 1);
				} else {
					countbyTeam.put(teamId, 1);
				}
			});

			// get each team definition
			for (ModelTeam team : teamDefinition.getTeams()) {

				MatchReadyToStartTeamInformationVO item = new MatchReadyToStartTeamInformationVO();
				item.teamID = team.getId();
				item.minParticipants = team.getMinParticipants();
				item.maxParticipants = team.getMaxParticipants();

				if( countbyTeam.containsKey(team.getId())) {
					item.participants = countbyTeam.get(team.getId());
				} else {
					item.participants = 0; 
				}
				
				teamInfo.add(item);
			}
		}
		
		readyToStart.teamParticipants = teamInfo.toArray(readyToStart.teamParticipants);
		return readyToStart;
	}

	public void mainLoop() {
		CheckRespawnAction respawnAction = new CheckRespawnAction(this);
		RemoveDeadAction removeDeadAction = new RemoveDeadAction(this);
		status = ThreadStatus.WAITING;

		logger.info("Waiting for the minimum participants: " + gameDefinition.getMinParticipants());

		MatchReadyToStartVO readinessInfo = readyToStartMatch();
		onCheckingReadiness.onNext(readinessInfo);
		
		while (alive && !readinessInfo.ready) {
			try {
				Thread.sleep(DEEP_BREATH_SLEEP);
				readinessInfo = readyToStartMatch();
				onCheckingReadiness.onNext(readinessInfo);

			} catch (InterruptedException e) {
				logger.error("Interrupted while waiting for participants", e);
			}
		}

		logger.info("[Bruce Buffer voice] It's TIME, starting game: " + JSONFormat.clean(gameDefinition.toString()));

		onMatchStart.onNext(match);
		onMatchStart.onComplete();

		getEventHandler().start();

		long timeStart = System.currentTimeMillis();
		this.timeElapsed = 0;

		long logStart = 0;
		long logThreshold = 30000;

		long start = 0;
		long elapsed = 0;
		long expectedElapsed = 0;
		int expectedFrameProcessingTime = 1000 / gameDefinition.getFps().intValue();
		delta = expectedFrameProcessingTime / 1000.0;

		logger.info("Starting Match mainLoop()");
		status = ThreadStatus.RUNNING;

		while (alive) {
			start = System.currentTimeMillis();
			timeElapsed = System.currentTimeMillis() - timeStart;

			// only controls match duration if gamedefinition.duration > 0
			if (gameDefinition.getDuration() > 0) {
				if (timeElapsed > gameDefinition.getDuration()) {
					logger.info(
							"end of match time elapsed time:" + timeElapsed + " max:" + gameDefinition.getDuration());
					break;
				}
			}

			if (logger.isInfoEnabled()) {
				if ((System.currentTimeMillis() - logStart) > logThreshold) {
					logStart = System.currentTimeMillis();
					logger.info("MatchRunner active: " + gameDefinition.getName() + " FPS: " + gameDefinition.getFps());
				}
			}

			try {
				if (logger.isDebugEnabled()) {
					logger.debug("**** TICK ****");
				}
				runOnActive.stream().sequential().forEach(action -> runAllActive(action));

				bulletsProcessor.process();
				punchesProcessor.process();

				// handle the dead guys
				runAll(removeDeadAction);
				runAll(respawnAction);
				runAll(ReduceCoolDownAction.getInstance());

				publisher.update(this);
				monitor.tick();

			} catch (Throwable e) {
				logger.error("*** ERROR AT MATCHRUN MAINLOOP", e);
			}

			elapsed = System.currentTimeMillis() - start;
			expectedElapsed = expectedFrameProcessingTime - elapsed;

			// adjust the FPS
			try {
				if (expectedElapsed > 0) {
					Thread.sleep(expectedElapsed);
				}
			} catch (InterruptedException e) {
				logger.error("Main loop interrupted", e);
			}
		}

		alive = false;
		try {
			publisher.update(this);
		} catch (Exception e) {
			logger.error("*** ERROR last publisher update", e);
		}

		logger.info("START matchrun shutdown match.id=" + match.getId());
		onMatchEnd.onNext(match);
		onMatchEnd.onComplete();

		// send end event and shut down event handler
		getEventHandler().end(new RunAfterThisTask(this) {
			public void run() {
				logger.info("matchrun shutdown (2)");
				((MatchRunner) data).shutDownServices();
			}
		});

	}

	public void shutDownServices() {
		logger.info("matchrun shutdown (3)");

		eventHandler.cleanup();
		luchadorCreator.cleanup();

		cleanup();
	}

	public void cleanup() {
		logger.info("matchrun shutdown (6)");

		if (cleanupActive) {
			cleanupActions();
		} else {
			logger.info("cleanup is not active, running TESTS? (9)");
		}
	}

	private void cleanupActions() {
		logger.info("matchmonitor remove (7)");

		logger.info("matchrun shutdown (8) luchador runners cleanup");
		LuchadorRunner[] localRunners = new LuchadorRunner[runners.values().size()];
		localRunners = runners.values().toArray(localRunners);

		for (int i = 0; i < localRunners.length; i++) {
			LuchadorRunner runner = localRunners[i];
			runner.cleanup();
		}

		listeners.stream().forEach(disposable -> disposable.dispose());

		// cleanup
		runners.clear();
		bullets.clear();
		punches.clear();
		punchesProcessor.cleanup();
		bulletsProcessor.cleanup();

		eventListeners.clear();
		matchEventListeners.clear();
		respawnProcessor.cleanup();
		listeners.clear();

		runners = null;
		bullets = null;
		punches = null;
		punchesProcessor = null;
		bulletsProcessor = null;

		eventListeners = null;
		matchEventListeners = null;
		respawnProcessor = null;
		listeners = null;

		gameDefinition = null;

		eventHandler = null;
		luchadorCreator = null;

		logger.info("matchrun shutdown (9)");
		
		onRunnerShutdown.onNext(getThreadName());
		onRunnerShutdown.onComplete();

	}

	public void runAllActive(GameAction action) {

		LuchadorRunner runner = null;

		if (logger.isDebugEnabled()) {
			logger.debug("runallactive : runners.size() = " + runners.size() + " action=" + action);
		}

		LuchadorRunner[] localRunners = new LuchadorRunner[runners.values().size()];
		localRunners = runners.values().toArray(localRunners);

		for (int i = 0; i < localRunners.length; i++) {
			runner = localRunners[i];

			if (logger.isDebugEnabled()) {
				logger.debug("runallactive : runner = " + runner);
				logger.debug("runallactive : runner.isActive() = " + runner.isActive());
				logger.debug(action.getName() + "-" + runner.getState());
			}

			if (runner != null && runner.isActive()) {

				try {
					action.run(runners, runner);
				} catch (Exception e) {
					runner.deactivate(action.getName());
					runner.onDangerMessage(MessageVO.DANGER, action.getName(), e.getMessage());

					logger.error("error executing action: " + action.getName() + " on luchador: "
							+ runner.getGameComponent().getId(), e);
				}
			}

		}

		localRunners = null;

	}

	// TODO: merge with runallactive
	void runAll(GameAction action) {

		LuchadorRunner runner = null;

		if (logger.isDebugEnabled()) {
			logger.debug("runallactive : runners.size() = " + runners.size());
		}

		LuchadorRunner[] localRunners = new LuchadorRunner[runners.values().size()];
		localRunners = runners.values().toArray(localRunners);

		for (int i = 0; i < localRunners.length; i++) {
			runner = localRunners[i];

			if (logger.isDebugEnabled()) {
				logger.debug("runallactive : runner = " + runner);
			}

			if (runner != null) {

				try {
					action.run(runners, runner);

				} catch (Exception e) {
					runner.deactivate(action.getName());
					runner.onDangerMessage(MessageVO.DANGER, action.getName(), e.getMessage());

					logger.error("erro executando acao :" + action.getName() + " no lutchador : "
							+ runner.getGameComponent().getId(), e);
				}
			}

		}

		localRunners = null;

	}

	public double getDelta() {
		return delta;
	}

	public ModelGameDefinition getGameDefinition() {
		return gameDefinition;
	}

	/**
	 * verifica se pode mover para determinada posicao
	 *
	 * @param source
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean canMove(LuchadorRunner source, double x, double y) {

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("START canMove: [%s, %s] %s", x, y, source));
			logger.debug(String.format("runners size [%s]", runners.keySet().size()));
		}

		boolean result = true;
		double radius = source.getSize() / 2;

		// needs to go over the entire list to check for collision
		Iterator<ModelSceneComponent> sceneIterator = getSceneComponents().iterator();
		while (sceneIterator.hasNext()) {
			ModelSceneComponent current = sceneIterator.next();

			if (logger.isDebugEnabled()) {
				logger.debug(">> (1) checking collision from :" + source.getGameComponent().getId() + " at x,y=" + x
						+ "," + y + " to scenecomponent : " + JSONFormat.clean(current.toString()));
			}

			if (current.isColider() && Calc.intersectCirclewithSceneComponent(x, y, radius, current)) {
				
				if (logger.isDebugEnabled()) {
					logger.debug("COLLISION from :" + source.getGameComponent().getId() + " at x,y=" + x
							+ "," + y + " to scenecomponent : " + JSONFormat.clean(current.toString()));
				}

				eventsRunner.onHit(current, source);

				if (WALL_TYPE.equals(current.getType())) {
					source.addEvent(new OnHitWallEvent(source.getState().getPublicState()));
				}

				if (current.isBlockMovement()) {
					result = false;
				}
			}
		}

		Iterator<Integer> iterator = runners.keySet().iterator();
		while (iterator.hasNext()) {
			Integer key = iterator.next();
			LuchadorRunner target = runners.get(key);

			if (logger.isDebugEnabled()) {
				logger.debug(">> verificando colisao de :" + source.getGameComponent().getId() + " em x,y=" + x + ","
						+ y + " no lutchador : " + target.getGameComponent().getId());
			}

			if (target != null && target.isActive()
					&& source.getGameComponent().getId() != target.getGameComponent().getId()) {

				if (logger.isDebugEnabled()) {
					boolean colide = Calc.intersectRobot(x, y, source, target);
					logger.debug(">>> realizando teste da colisao com : " + target);
					if (colide) {
						logger.debug(">>> COLIDE ");
					}
				}

				try {
					// se colidiu com outros robos gera evento de colisao
					// em cada um
					//
					if (Calc.intersectRobot(x, y, source, target)) {
						source.addEvent(new OnHitOtherEvent(source.getState().getPublicState(),
								target.getState().getPublicState()));

						target.addEvent(new OnHitOtherEvent(source.getState().getPublicState(),
								source.getState().getPublicState()));

						if (logger.isDebugEnabled()) {
							logger.debug("+++ ouch !");
						}
						result = false;
					}

				} catch (Exception e) {
					logger.debug("erro verificando colisao de :" + source.getGameComponent().getId() + "em x,y=" + x
							+ "," + y + " no lutchador : " + target.getGameComponent().getId());
				}
			}

		}

		return result;
	}

	public void addLuchadorEvent(LuchadorEvent event) {
		LuchadorEventHandler handler = new LuchadorEventHandler(event, eventListeners);
		handler.start();
	}

	public void addListener(LuchadorEventListener listener) {
		eventListeners.add(listener);
	}

	public void addListener(MatchEventListener listener) {
		matchEventListeners.add(listener);
	}

	public void listen(LuchadorRunner runner, String message) {
		runAllActive(new AddOnListenEventAction(runner.getState().getPublicState(), message));
	}

	public RespawnPoint getRespawnPoint(LuchadorRunner runner) {
		return respawnProcessor.getRespawnPoint(runner, runners);
	}

	public String getThreadStatus() {
		return status;
	}

	public String getThreadName() {
		return threadName;
	}

	public Long getThreadStartTime() {
		return startTime;
	}

	public List<MatchEventListener> getMatchEventListeners() {
		return matchEventListeners;
	}

	public SafeList getBullets() {
		return bullets;
	}

	public SafeList getPunches() {
		return punches;
	}

	public LinkedHashMap<Integer, LuchadorRunner> getRunners() {
		return runners;
	}

	public boolean isAlive() {
		return alive;
	}

	public void kill() {
		alive = false;
	}

	public long getTimeElapsed() {
		return timeElapsed;
	}

	private long lastTimeAlive;

	@Override
	public void setLastAlive(long lastTimeAlive) {
		this.lastTimeAlive = lastTimeAlive;
	}

	@Override
	public long getLastAlive() {
		return lastTimeAlive;
	}

	public void setPublisher(MatchStatePublisher publisher) {
		this.publisher = publisher;
	}

	public PublishSubject<ModelMatch> getOnMatchStart() {
		return onMatchStart;
	}

	public PublishSubject<ModelMatch> getOnMatchEnd() {
		return onMatchEnd;
	}

	public PublishSubject<String> getOnRunnerShutdown() {
		return onRunnerShutdown;
	}

	public PublishSubject<MessageVO> getOnMessage() {
		return onMessage;
	}

	public PublishSubject<MatchReadyToStartVO> getOnCheckingReadiness() {
		return onCheckingReadiness;
	}

	public PublishSubject<MatchInitVO> getOnInit() {
		return onInit;
	}

	public IMatchRunnerAPI getApi() {
		return api;
	}
}
