package com.robolucha.runner;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.robolucha.event.match.MatchEventVO;
import com.robolucha.event.match.MatchEventVOEnd;
import com.robolucha.event.match.MatchEventVOStart;
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
import com.robolucha.game.vo.MessageVO;
import com.robolucha.listener.JoinMatchListener;
import com.robolucha.models.Bullet;
import com.robolucha.monitor.ServerMonitor;
import com.robolucha.monitor.ThreadMonitor;
import com.robolucha.monitor.ThreadStatus;
import com.robolucha.publisher.MatchStatePublisher;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.runner.SceneComponentEventsRunner.EventType;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.runner.luchador.LutchadorRunnerCreator;
import com.robolucha.shared.Calc;
import com.robolucha.shared.JSONFormat;

import io.reactivex.subjects.PublishSubject;
import io.swagger.client.model.MainGameComponent;
import io.swagger.client.model.MainGameDefinition;
import io.swagger.client.model.MainMatch;
import io.swagger.client.model.MainSceneComponent;

/**
 * main match logic
 */
public class MatchRunner implements Runnable, ThreadStatus {

	private static final long SMALL_SLEEP = 5;
	private static final String WALL_TYPE = "wall";

	private SafeList bullets;
	private SafeList punches;

	private List<GameAction> runOnActive;
	private PunchesProcessor punchesProcessor;
	private BulletsProcessor bulletsProcessor;
	private double delta;

	// TODO: replace all the listeners by Subjects
	private PublishSubject<MatchEventVO> onMatchStart;
	private PublishSubject<MatchEventVO> onMatchEnd;
	private PublishSubject<MessageVO> onMessage;
	private PublishSubject<MatchInitVO> onInit;

	private List<MatchRunnerListener> listeners;

	private MainGameDefinition gameDefinition;

	private MatchStatePublisher publisher;
	private JoinMatchListener joinListener;

	public void setJoinListener(JoinMatchListener joinListener) {
		this.joinListener = joinListener;
	}

	private List<LuchadorEventListener> eventListeners;
	private List<MatchEventListener> matchEventListeners;

	static Logger logger = Logger.getLogger(MatchRunner.class);

	LinkedHashMap<Integer, LuchadorRunner> runners;
	List<MainSceneComponent> sceneComponents;

	boolean alive;
	private IRespawnProcessor respawnProcessor;

	private String status;
	private String threadName;
	private Long startTime;
	// private MatchRun match;
	private long timeElapsed;
	private boolean cleanupActive = true;

	private MatchEventHandler eventHandler;
	private LutchadorRunnerCreator luchadorCreator;
	private SceneComponentEventsRunner eventsRunner;
	private MainMatch match;
	private ServerMonitor monitor;

	public MatchEventHandler getEventHandler() {
		return eventHandler;
	}

	public MatchRunner(MainGameDefinition gameDefinition, MainMatch match, RemoteQueue queue, ServerMonitor monitor) {
		threadName = this.getClass().getName() + "-" + ThreadMonitor.getUID();

		status = ThreadStatus.STARTING;
		alive = true;
		delta = 0.0;
		this.gameDefinition = gameDefinition;

		this.match = match;

		listeners = new LinkedList<MatchRunnerListener>();

		runOnActive = new LinkedList<GameAction>();
		runOnActive.add(new RepeatAction());
		runOnActive.add(new CheckRadarAction(this));
		runOnActive.add(new TriggerEventsAction());
		runOnActive.add(new ChangeStateAction());

		runners = new LinkedHashMap<Integer, LuchadorRunner>();
		sceneComponents = new LinkedList<MainSceneComponent>();
		addSceneComponents();
		eventsRunner = new SceneComponentEventsRunner();

		respawnProcessor = RespawnProcessorFactory.get(this);

		bullets = new SafeList(new LinkedList<Bullet>());
		punches = new SafeList(new LinkedList<Punch>());

		eventListeners = Collections.synchronizedList(new LinkedList<LuchadorEventListener>());
		matchEventListeners = Collections.synchronizedList(new LinkedList<MatchEventListener>());

		punchesProcessor = new PunchesProcessor(this, punches);
		bulletsProcessor = new BulletsProcessor(this, bullets);

		eventHandler = new MatchEventHandler(this, threadName, monitor);
		luchadorCreator = new LutchadorRunnerCreator(this, queue, monitor);

		onMatchStart = PublishSubject.create();
		onMatchEnd = PublishSubject.create();
		onMessage = PublishSubject.create();
		onInit = PublishSubject.create();

		logger.info("MatchRunner created:" + this);
	}

	private void addSceneComponents() {
		sceneComponents.addAll(gameDefinition.getSceneComponents());
	}

	public List<MainSceneComponent> getSceneComponents() {
		return sceneComponents;
	}

	public MainMatch getMatch() {
		return match;
	}

	public void addListener(MatchRunnerListener listener) {
		listeners.add(listener);
		listener.subscribe(this);
	}

	public PublishSubject<LuchadorRunner> add(final MainGameComponent component) throws Exception {

		if (runners.containsKey(component.getId())) {
			String message = "trying to add luchador that is already in the match, id: " + component.getId();
			logger.info(message);

			PublishSubject<LuchadorRunner> result = PublishSubject.create();
			result.onError(new Exception(message));
			result.onComplete();
			return result;
		}

		if (runners.size() >= gameDefinition.getMaxParticipants()) {
			String message = "trying to add luchador beyond the limit";
			PublishSubject<LuchadorRunner> result = PublishSubject.create();
			result.onError(new Exception(message));
			result.onComplete();
			return result;
		}

		logger.info("new luchador added to the match: " + JSONFormat.clean(component.toString()));
		return luchadorCreator.add(component);
	}

	public void addLuchador(final MainGameComponent component) throws Exception {
		add(component);
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
		Thread.currentThread().setName("MatchRunner-Thread" + gameDefinition.getName());

		startTime = System.currentTimeMillis();
		this.onInit.onNext(new MatchInitVO(startTime));
		this.onInit.onComplete();

		// TODO: add observable to the init procedures
		// getOnInit()... is it completed?

		mainLoop();
	}

	public void mainLoop() {
		CheckRespawnAction respawnAction = new CheckRespawnAction(this);
		RemoveDeadAction removeDeadAction = new RemoveDeadAction(this);

		logger.info("Waiting for the minimum participants: " + gameDefinition.getMinParticipants());

		while (alive && runners.size() < gameDefinition.getMinParticipants()) {
			try {
				Thread.sleep(SMALL_SLEEP);
			} catch (InterruptedException e) {
				logger.error("Interrupted while waiting for participants", e);
			}
		}

		logger.info("[Bruce Buffer voice] It's TIME, starting game: " + JSONFormat.clean(gameDefinition.toString()));

		// TODO: reduce to one single event
		onMatchStart.onNext(new MatchEventVOStart());
		onMatchStart.onComplete();

		getEventHandler().start();

		long timeStart = System.currentTimeMillis();
		this.timeElapsed = 0;

		long logStart = 0;
		long logThreshold = 30000;

		long start = System.currentTimeMillis();
		long current = 0;
		long elapsed = 0;
		int expectedElapsed = 0;

		logger.info("starting");

		while (alive) {
			current = System.currentTimeMillis();
			elapsed = current - start;
			start = current;
			delta = elapsed / 1000.0;
			expectedElapsed = (int) ((1000 / gameDefinition.getFps()) - elapsed);

			try {
				// adjust the FPS

				// TODO: allow inspection of this value
				if (expectedElapsed > 0) {
					Thread.sleep(expectedElapsed);
				}
			} catch (InterruptedException e) {
				logger.error("Main loop interrupted", e);
			}

			timeElapsed = System.currentTimeMillis() - timeStart;

			// only controls match duration if gamedefinition.duration > 0
			if (gameDefinition.getDuration() > 0) {
				if (timeElapsed > gameDefinition.getDuration()) {
					logger.info(
							"end of match time elapsed time:" + timeElapsed + " max:" + gameDefinition.getDuration());
					break;
				}
			}

			if ((System.currentTimeMillis() - logStart) > logThreshold) {
				logStart = System.currentTimeMillis();
				logger.info("MatchRunner active: " + gameDefinition.getName() + " FPS: " + gameDefinition.getFps());
				// getEventHandler().alive();
			}

			try {
				if (logger.isDebugEnabled()) {
					logger.debug("**** TICK ****");
				}

				runOnActive.stream().sequential().forEach(action -> runAllActive(action));

				bulletsProcessor.process();
				punchesProcessor.process();

				// trata os mortinhos da silva
				runAll(removeDeadAction);
				runAll(respawnAction);

				// atualiza tempos de cooldown
				runAll(ReduceCoolDownAction.getInstance());

				publisher.update(this);

			} catch (Throwable e) {
				logger.error("*** ERRO NO LOOP DO MATCHRUN", e);
			}

		}

		onMatchEnd.onNext(new MatchEventVOEnd());
		onMatchEnd.onComplete();

		logger.info("matchrun shutdown (1)");
		// desliga tratador de eventos
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

		if (joinListener != null) {
			joinListener.dispose();
		}

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
			// runners.put(runner.getGameComponent().getId(), null);
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

	public MainGameDefinition getGameDefinition() {
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
		Iterator<MainSceneComponent> sceneIterator = sceneComponents.iterator();
		while (sceneIterator.hasNext()) {
			MainSceneComponent current = sceneIterator.next();
			if (current.isColider() && Calc.intersectCirclewithSceneComponent(x, y, radius, current)) {
				eventsRunner.onEvent(EventType.ON_HIT, current, source);

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

	public LuchadorRunner getRunner(Long luchadorId) {
		if (runners == null) {
			return null;
		}
		return runners.get(luchadorId);
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

	public PublishSubject<MatchEventVO> getOnMatchStart() {
		return onMatchStart;
	}

	public PublishSubject<MatchEventVO> getOnMatchEnd() {
		return onMatchEnd;
	}

	public PublishSubject<MessageVO> getOnMessage() {
		return onMessage;
	}

	public PublishSubject<MatchInitVO> getOnInit() {
		return onInit;
	}
}
