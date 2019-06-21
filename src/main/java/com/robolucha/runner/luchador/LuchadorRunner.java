package com.robolucha.runner.luchador;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.robolucha.event.ConstEvents;
import com.robolucha.event.GeneralEventHandler;
import com.robolucha.event.GeneralEventManager;
import com.robolucha.game.event.LuchadorEvent;
import com.robolucha.game.event.OnHitWallEvent;
import com.robolucha.game.vo.MessageVO;
import com.robolucha.listener.LuchadorUpdateListener;
import com.robolucha.models.Bullet;
import com.robolucha.models.LuchadorMatchState;
import com.robolucha.models.LuchadorPublicState;
import com.robolucha.models.MatchStateProvider;
import com.robolucha.models.ScoreVO;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.RespawnPoint;
import com.robolucha.runner.code.LuchadorScriptDefinition;
import com.robolucha.runner.code.MethodBuilder;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.runner.code.ScriptDefinitionFactory;
import com.robolucha.shared.Calc;

import io.swagger.client.model.MainCode;
import io.swagger.client.model.MainGameComponent;

/**
 * Represents one play thread during the match
 *
 * @author hamiltonlima
 */
public class LuchadorRunner implements GeneralEventHandler, MatchStateProvider {

	public static final double MAX_EVENTS_PER_TICK = 5;

	public static final String EVENT_ONHITWALL = "onHitWall";

	public static final String DEAD = "dead";

	public static final String COMMAND_FIRE = "fire";
	public static final String COMMAND_MOVE = "move";
	public static final String COMMAND_TURN = "turn";
	public static final String COMMAND_TURNGUN = "turnGun";

	public static final String ACTION_CHECK_RADAR = "checkRadar";
	private static final double DOUBLE_MIN_THRESHOLD = 0.01;
	private static final int MAX_COLOR_LENGTH = 7;

	private static Logger logger = Logger.getLogger(LuchadorRunner.class);

	// test classes can update this.
	MainGameComponent gameComponent;

	private boolean active;
	private long start;
	private long elapsed;
	private String lastRunningError;

	private Map<String, LuchadorCodeExecution> codeExecutionQueue;
	Map<String, LuchadorEvent> events;
	private Queue<MessageVO> messages;

	private int size;
	private int halfSize;

	private LuchadorScriptDefinition scriptDefinition;
	private int exceptionCounter;

	private LuchadorMatchState state;

	public boolean cleanUpStateAtTheEnd = true;

	private MatchRunner matchRunner;
	private double fireCoolDown;
	private double punchCoolDown;
	private double respawnCoolDown;
	LuchadorScriptRunner currentRunner;

	private LuchadorUpdateListener luchadorUpdatelistener;

	public LuchadorRunner(MainGameComponent component, MatchRunner matchRunner) {
		this.gameComponent = component;
		this.matchRunner = matchRunner;
		this.setExceptionCounter(0);

		this.size = matchRunner.getGameDefinition().getLuchadorSize();
		this.halfSize = this.size / 2;

		this.active = false;
		this.codeExecutionQueue = Collections.synchronizedMap(new LinkedHashMap<String, LuchadorCodeExecution>());
		this.events = Collections.synchronizedMap(new LinkedHashMap<String, LuchadorEvent>());
		this.messages = new LinkedList<MessageVO>();

		scriptDefinition = ScriptDefinitionFactory.getInstance().getLuchadorScript();

		state = new LuchadorMatchState();
		state.setId(component.getId());
		state.setName(component.getName());

		try {
			setDefaultState(matchRunner.getRespawnPoint(this));
			setDefaultScore();
			createCodeEngine(component.getCodes());
			this.active = true;
		} catch (Exception e) {
			logger.error("error on luchador constructor: " + component, e);
		}

		// listen to luchador name change
		// TODO: Remove this?
		GeneralEventManager.getInstance().addHandler(ConstEvents.LUCHADOR_NAME_CHANGE, this);
	}

	// used for tests only
	void updateCodeEngine() throws Exception {
		createCodeEngine(gameComponent.getCodes());
	}

	public void cleanup() {
		logger.info("*** luchador CLEAN-UP " + this.getGameComponent().getId());

		this.active = false;

		if (cleanUpStateAtTheEnd) {
			this.state.score = null;
			this.state = null;
		}

		if (luchadorUpdatelistener != null) {
			luchadorUpdatelistener.dispose();
			luchadorUpdatelistener = null;
		}

		this.gameComponent = null;
		this.matchRunner = null;

		this.codeExecutionQueue.clear();
		this.events.clear();
		this.messages.clear();

		this.codeExecutionQueue = null;
		this.events = null;
		this.messages = null;

	}

	/**
	 * handle name change events
	 */
	@Override
	public void handle(String event, Object data) {

		if (logger.isDebugEnabled()) {
			logger.debug("handle de evento event=" + event);
			logger.debug("handle de evento data=" + data);
			logger.debug("gameComponent=" + this.gameComponent);
		}

		if (ConstEvents.LUCHADOR_NAME_CHANGE.equals(event)) {

			if (data != null && this.gameComponent != null) {
				MainGameComponent changed = (MainGameComponent) data;
				if (changed.getId() == this.gameComponent.getId()) {
					if (logger.isDebugEnabled()) {
						logger.debug("*** same id, change ScoreVO");
					}
					this.state.setName(changed.getName());
					this.gameComponent.setName(changed.getName());
				}
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("this.score=" + this.state.score);
		}
	}

	public ScoreVO getScoreVO() {
		return this.state.score;
	}

	/**
	 * updates luchador code change on the fly
	 *
	 * @param luchador
	 */
	public void update(MainGameComponent luchador) {
		logger.info("*** luchador UPDATE " + luchador.getId());

		try {
			this.gameComponent = luchador;
			this.messages.clear();

			List<MainCode> codes4CurrentGameDefinition = luchador.getCodes().stream()
					.filter(line -> matchRunner.getGameDefinition().getId().equals(line.getGameDefinition()))
					.collect(Collectors.toList());

			logger.info("new code" + codes4CurrentGameDefinition);
			updateCodeEngine(codes4CurrentGameDefinition);

		} catch (Exception e) {
			this.active = false;
			logger.warn("error updating code: " + luchador);
			// TODO: send feedback to the user
		}
	}

	private void updateScriptState(LuchadorPublicState state) throws Exception {
		scriptDefinition.set("me", state);
	}

	private void createCodeEngine(List<MainCode> list) throws Exception {

		logger.debug("START createCodeEngine()");
		start = System.currentTimeMillis();
		try {

			updateScriptState(state.getPublicState());
			scriptDefinition.set("ARENA_WIDTH", matchRunner.getGameDefinition().getArenaWidth());
			scriptDefinition.set("ARENA_HEIGHT", matchRunner.getGameDefinition().getArenaHeight());
			scriptDefinition.set("RADAR_ANGLE", matchRunner.getGameDefinition().getRadarAngle());
			scriptDefinition.set("RADAR_RADIUS", matchRunner.getGameDefinition().getRadarRadius());
			scriptDefinition.set("LUCHADOR_WIDTH", getSize());
			scriptDefinition.set("LUCHADOR_HEIGHT", getSize());

			scriptDefinition.loadDefaultLibraries();

			MethodBuilder.getInstance().buildAll(scriptDefinition, list);
			updateInvalidCodes(list);

		} catch (Exception e) {
			logger.error("error running code initialization", e);
			throw e;
		} finally {
			scriptDefinition.afterCompile();
		}

		elapsed = System.currentTimeMillis() - start;
		logger.info("script run in " + elapsed + " ms.");
		logger.debug("END createCodeEngine()");
	}

	public void updateCodeEngine(List<MainCode> list) throws Exception {

		logger.debug("START updateCodeEngine()");
		this.active = false;
		this.lastRunningError = null;

		try {
			createCodeEngine(list);
			this.active = true;
		} catch (Exception e) {
			logger.error("error running code initialization", e);
			throw e;
		}

		logger.debug("END createCodeEngine()");

	}

	private void setDefaultState(RespawnPoint point) {
		state.setX(point.x);
		state.setY(point.y);
		state.setLife(matchRunner.getGameDefinition().getLife());
		state.setAngle(point.angle);
		state.setGunAngle(point.gunAngle);
		state.setFireCoolDown(0);

		respawnCoolDown = 0.0;
		fireCoolDown = 0.0;
		punchCoolDown = 0.0;
	}

	private void setDefaultScore() {
		state.score = new ScoreVO(gameComponent.getId(), gameComponent.getName());
	}

	String getString(String script) throws Exception {
		return scriptDefinition.getString(script);
	}

	private void updateInvalidCodes(List<MainCode> list) throws Exception {

		if (list == null) {
			logger.warn("list of codes is empty.");
			return;
		}

		Iterator<MainCode> iterator = list.iterator();
		while (iterator.hasNext()) {
			MainCode code = iterator.next();
			if (code.getException() != null && code.getException().trim().length() > 0) {
				onDangerMessage(MessageVO.DANGER, code.getEvent(), code.getException());
			}

		}

	}

	public void saveExceptionToCode(String name, String exception) {
		if (logger.isDebugEnabled()) {
			logger.debug("save exception to " + name + " exception=" + exception);
		}

		onDangerMessage(MessageVO.DANGER, name, exception);

		for (MainCode code : getGameComponent().getCodes()) {
			if (code.getEvent().equals(name)) {
				code.setException(exception);

				if (logger.isDebugEnabled()) {
					logger.debug("code atualizado " + code);
				}

				break;
			}
		}
	}

	/**
	 * Run the code in a separated thread
	 *
	 * @param codeName
	 * @param parameter
	 * @return
	 * @throws NoSuchMethodException
	 */
	public void run(String codeName, Object... parameter) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Trying to run " + codeName + " currentRunner=" + currentRunner);
		}

		// TODO: control running time to deactivate luchador
		if (currentRunner == null || currentRunner.isFinished()) {

			// check if the code has exception to avoid running defective code
			boolean canRunCode = true;
			for (MainCode code : getGameComponent().getCodes()) {
				if (code.getEvent().equals(codeName)) {
					if (code.getException() != null && code.getException().trim().length() > 0) {
						canRunCode = false;
						break;
					}
				}
			}

			if (logger.isDebugEnabled()) {
				logger.debug("Trying to run " + codeName + ", code exception, canRunCode=" + canRunCode);
			}

			// check if Runner is already running this piece of code
			if (codeExecutionQueue.get(codeName) != null) {
				canRunCode = false;
			}

			if (logger.isDebugEnabled()) {
				logger.debug(
						"Trying to run " + codeName + ", search in the code execution queue, canRunCode=" + canRunCode);
			}

			if (canRunCode) {
				currentRunner = new LuchadorScriptRunner(this, codeName, parameter);
				Thread thread = new Thread(currentRunner);
				thread.start();
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("current runner exists and is still BUSY, currentRunner=" + currentRunner);
			}
		}
	}

	private static final Object[] EMPTY = new Object[0];

	public void run(String name) throws Exception {
		run(name, EMPTY);
	}

	public MainGameComponent getGameComponent() {
		return gameComponent;
	}

	public boolean isActive() {
		return active;
	}

	public long getStart() {
		return start;
	}

	public long getElapsed() {
		return elapsed;
	}

	public LuchadorMatchState getState() {
		return state;
	}

	public void kill() {
		deactivate(DEAD);
		this.respawnCoolDown = matchRunner.getGameDefinition().getRespawnCooldown();
	}

	public double getRespawnCoolDown() {
		return respawnCoolDown;
	}

	public String getLastRunningError() {
		return lastRunningError;
	}

	public void deactivate(String reason) {
		logger.debug("deactivate=" + reason);

		this.active = false;
		this.lastRunningError = reason;

		this.codeExecutionQueue.clear();
		this.events.clear();
		this.messages.clear();
	}

	public void executeMove(double amount) {

		double addX = Math.cos(Calc.toRadian(state.getAngle())) * amount;
		double addY = Math.sin(Calc.toRadian(state.getAngle())) * amount;

		if (logger.isDebugEnabled()) {
			logger.debug("**** executeMove =" + amount);
			logger.debug(String.format("**** angle=%s addX=%s addY=%s", state.getAngle(), addX, addY));
		}

		if (Math.abs(addX) < DOUBLE_MIN_THRESHOLD) {
			addX = 0;
		}

		if (Math.abs(addY) < DOUBLE_MIN_THRESHOLD) {
			addY = 0;
		}

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("after check DOUBLE_MIN_THRESHOLD, angle=%s addX=%s addY=%s", state.getAngle(),
					addX, addY));
		}

		double x = state.getX() + addX;
		double y = state.getY() + addY;

		if (logger.isDebugEnabled()) {
			logger.debug("move to x=" + x + ", y=" + y);
		}

		if (insideTheMapLimits(x, y) && matchRunner.canMove(this, x, y)) {
			this.state.setX(x);
			this.state.setY(y);
		}
	}

	/**
	 * check if luchador is inside the map limits, trigger onHitWall if necessary
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean insideTheMapLimits(double x, double y) {

		if (!Calc.insideTheMapLimits(matchRunner.getGameDefinition().getArenaWidth(),
				matchRunner.getGameDefinition().getArenaHeight(), x, y, halfSize)) {

			if (logger.isDebugEnabled()) {
				logger.debug("not inside the map limits: x=" + x + ",y=" + y);
			}

			addEvent(new OnHitWallEvent(getState().getPublicState()));
			return false;
		}

		return true;
	}

	/**
	 * add event if is not in the event list
	 *
	 * @param event .getKey()
	 */
	public void addEvent(LuchadorEvent event) {

		if (events.get(event.getKey()) == null) {
			events.put(event.getKey(), event);
		}
	}

	/**
	 * Allow the event processor to get the first in the queue of events
	 *
	 * @return
	 */
	private LuchadorEvent getTopEvent() {

		Iterator<Entry<String, LuchadorEvent>> iterator = events.entrySet().iterator();
		if (iterator.hasNext()) {
			Entry<String, LuchadorEvent> next = iterator.next();
			iterator.remove();
			return next.getValue();
		}

		return null;
	}

	public void executeTurn(double amount) {
		double newAngle = this.getState().getAngle() + amount;
		newAngle = Calc.fixAngle(newAngle);
		this.getState().setAngle(newAngle);
	}

	public void executeTurnGun(double amount) {

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("execute turn gun, amount : %s", amount));
		}

		double newAngle = this.getState().getGunAngle() + amount;
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("execute turn gun, new angle : %s", newAngle));
		}

		newAngle = Calc.fixAngle(newAngle);
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("execute turn gun, after validation, new angle : %s", newAngle));
		}

		this.getState().setGunAngle(newAngle);
	}

	public void triggerEvents() throws Exception {

		int counter = 0;
		LuchadorEvent event = getTopEvent();
		logger.debug("TriggerEvents " + event);
		while (event != null && counter < MAX_EVENTS_PER_TICK) {

			logger.debug("TriggerEvents (2)" + event);
			matchRunner.addLuchadorEvent(event);
			run(event.getJavascriptMethod(), event.getMethodParameters());
			event = getTopEvent();
			counter++;
		}
	}

	public void consumeCommand() {
		logger.debug("consumeCommand()");

		if (codeExecutionQueue.isEmpty()) {
			logger.debug("consumeCommand() codeExecutionQueue empty");
			return;
		}

		Iterator<LuchadorCodeExecution> iterator = codeExecutionQueue.values().iterator();
		LuchadorCodeExecution codeExecution = null;

		try {
			codeExecution = iterator.next();

			Iterator<LuchadorCommandQueue> commandIterator = codeExecution.getCommands().values().iterator();
			LuchadorCommandQueue command = null;

			while (commandIterator.hasNext()) {
				command = commandIterator.next();
				consumeCommand(commandIterator, command);
			}
		} catch (Exception e) {
			logger.error("Error reading first", e);
			logger.warn("Error reading the first LuchadorCodeExecution, try again.");
			return;
		}

		logger.debug("consumeCommand() action" + codeExecution);

		// the action dont have any commands to execute, so remove from the queue
		if (codeExecution.getCommands().size() == 0) {
			iterator.remove();
		}

	}

	private void consumeCommand(Iterator<LuchadorCommandQueue> iterator, LuchadorCommandQueue commandQueue) {
		LuchadorCommand command = commandQueue.getFirst();

		// The queue is empty
		if (command == null) {
			iterator.remove();
			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("consumeCommand() : command=" + command.getCommand() + " delta=" + matchRunner.getDelta()
					+ " amount=" + command.getValue() + " " + command);
		}

		if (command.getCommand().equals(COMMAND_MOVE)) {
			double amount = command.consume(matchRunner.getDelta());
			executeMove(amount);
		}

		if (command.getCommand().equals(COMMAND_TURN)) {
			double amount = command.consume(matchRunner.getDelta());
			executeTurn(amount);
		}

		if (command.getCommand().equals(COMMAND_TURNGUN)) {
			double amount = command.consume(matchRunner.getDelta());
			executeTurnGun(amount);
		}

		if (command.getCommand().equals(COMMAND_FIRE)) {
			command.consumeAll();

			// only if the fire cooldown is over
			if (fireCoolDown <= 0) {

				Bullet bullet = new Bullet(matchRunner.getGameDefinition().getBuletSpeed(),
						matchRunner.getGameDefinition().getBulletSize(), this, command.getOriginalValue(), state.getX(),
						state.getY(), state.getGunAngle());

				matchRunner.fire(bullet);

				// starts the timer for the firecooldown
				fireCoolDown = matchRunner.getGameDefinition().getMaxFireCooldown()
						* (bullet.getAmount() / matchRunner.getGameDefinition().getMaxFireAmount());
			}
		}

		if (command.empty()) {
			if (logger.isDebugEnabled()) {
				logger.debug("command empty");
			}
			commandQueue.remove(command);
		}

	}

	/**
	 * add a command to the commands queue only if:
	 * 
	 * - if the codeName is not in queue
	 *
	 * @param command
	 */
	public void addCommand(LuchadorCommand command) {

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("ADD Command( %s, %s) %s", command.getCommand(), command.getOriginalValue(),
					command));
		}

		// check if the action is already in the queue
		LuchadorCodeExecution codeExecution = codeExecutionQueue.get(command.getCodeName());
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("+++ current codeExecution=%s", codeExecution));
		}

		if (codeExecution == null) {
			codeExecution = new LuchadorCodeExecution(command.getCodeName(), this.start);
			codeExecutionQueue.put(command.getCodeName(), codeExecution);
		}

		codeExecution.add(command);
	}

	public void clearCommand(String prefix) {
		Iterator<String> iterator = codeExecutionQueue.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			LuchadorCodeExecution action = codeExecutionQueue.get(key);
			action.clear(prefix);
		}
	}

	/**
	 * keep the fire values in the correct range
	 *
	 * @param amount
	 * @return
	 */
	public int cleanUpAmount(int amount) {

		if (amount > matchRunner.getGameDefinition().getMaxFireAmount()) {
			amount = (int) matchRunner.getGameDefinition().getMaxFireAmount();
		} else {
			if (amount <= matchRunner.getGameDefinition().getMinFireAmount()) {
				amount = (int) matchRunner.getGameDefinition().getMinFireAmount();
			}
		}

		return amount;
	}

	public void updateFireCooldown() {
		if (this.fireCoolDown > 0) {
			this.fireCoolDown = this.fireCoolDown - matchRunner.getDelta();
		}

		this.state.setFireCoolDown(Calc.roundTo4Decimals(this.fireCoolDown));

		try {
			updateScriptState(state.getPublicState());
		} catch (Exception e) {
			logger.error("error update public state after changing firecooldown", e);

		} finally {
			scriptDefinition.afterCompile();
		}
	}

	public void updatePunchCooldown() {
		if (this.punchCoolDown > 0) {
			this.punchCoolDown = this.punchCoolDown - matchRunner.getDelta();
		}
	}

	public void updateRespawnCooldown() {
		if (this.respawnCoolDown > 0) {
			this.respawnCoolDown = this.respawnCoolDown - matchRunner.getDelta();
		}
	}

	// TODO: use codeName when punch is implemented
	public void punch(String codeName) {
		if (punchCoolDown <= 0) {
			matchRunner.punch(this, matchRunner.getGameDefinition().getPunchDamage(), state.getX(), state.getY(),
					state.getAngle());
			punchCoolDown = matchRunner.getGameDefinition().getPunchCoolDown();
		}
	}

	public void fix(String codeName, int lutchador) {
		// TODO: implement this
	}

	public void clearAllCommands() {
		codeExecutionQueue.clear();
	}

	public void say(String message) {
		matchRunner.listen(this, message);
	}

	public int getSize() {
		return size;
	}

	public void damage(double amount) {
		this.state.setLife(this.state.getLife() - amount);
	}

	public void respawn(RespawnPoint point) {
		setDefaultState(point);

		// make sure start is runned when respawn
		MethodBuilder.getInstance().build(scriptDefinition, getStartCode());

		this.lastRunningError = "";
		this.active = true;
	}

	/**
	 * @return
	 * @todo check
	 */
	private MainCode getStartCode() {

		if (this.gameComponent.getCodes() != null) {
			Iterator<MainCode> iterator = this.gameComponent.getCodes().iterator();
			while (iterator.hasNext()) {
				MainCode code = iterator.next();
				if (code.getEvent().equals(MethodNames.ON_START)) {
					return code;
				}
			}
		}
		return null;
	}

	public void onDangerMessage(String type, String event, String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("---- add DANGER message, event=%s, message=%s", event, message));
		}

		matchRunner.getOnMessage().onNext(new MessageVO(gameComponent.getId(), MessageVO.DANGER, event, message));
	}

	public void onMessage(String type, String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("---- add message, type=%s, message=%s", type, message));
		}

		matchRunner.getOnMessage().onNext(new MessageVO(gameComponent.getId(), type, message));
	}

	public void addFire(String codeName, int amount) {
		amount = cleanUpAmount(amount);
		LuchadorCommand command = new LuchadorCommand(codeName, COMMAND_FIRE, amount,
				matchRunner.getGameDefinition().getBuletSpeed());
		addCommand(command);
	}

	public void addMove(String codeName, int amount) {
		LuchadorCommand command = new LuchadorCommand(codeName, COMMAND_MOVE, amount,
				matchRunner.getGameDefinition().getMoveSpeed());
		addCommand(command);
	}

	public void addTurn(String codeName, int amount) {
		LuchadorCommand command = new LuchadorCommand(codeName, COMMAND_TURN, amount,
				matchRunner.getGameDefinition().getTurnSpeed());
		addCommand(command);
	}

	public void addTurnGun(String codeName, int amount) {
		LuchadorCommand command = new LuchadorCommand(codeName, COMMAND_TURNGUN, amount,
				matchRunner.getGameDefinition().getTurnGunSpeed());
		addCommand(command);
	}

	public int getExceptionCounter() {
		return exceptionCounter;
	}

	public long getId() {
		return gameComponent.getId();
	}

	public LuchadorScriptDefinition getScriptDefinition() {
		return scriptDefinition;
	}

	public void setUpdateListener(LuchadorUpdateListener luchadorUpdatelistener) {
		this.luchadorUpdatelistener = luchadorUpdatelistener;
	}

	public void setExceptionCounter(int exceptionCounter) {
		this.exceptionCounter = exceptionCounter;
	}

}
