package com.robolucha.runner.luchador.lua;

import org.apache.log4j.Logger;

import com.robolucha.game.vo.MessageVO;
import com.robolucha.runner.luchador.LuchadorRunner;

/**
 * 
 * @author hamiltonlima
 *
 */
public class LuaFacade implements ScriptFacade {

	private static Logger logger = Logger.getLogger(LuaFacade.class);
	private LuchadorRunner owner;
	private String lastCall = null;
	private String codeName;

	public LuaFacade(LuchadorRunner owner, String codeName) {
		this.owner = owner;
		this.codeName = codeName;
	}

	/* (non-Javadoc)
	 * @see com.robolucha.runner.luchador.lua.ScriptFacade#move(int)
	 */
	@Override
	public void move(int amount) {
		lastCall = "move(" + amount + ")";

		if (logger.isDebugEnabled()) {
			logger.debug(lastCall);
			logger.debug(">> running " + lastCall);
		}
		owner.addMove(codeName, amount);
	}

	/* (non-Javadoc)
	 * @see com.robolucha.runner.luchador.lua.ScriptFacade#stop()
	 */
	@Override
	public void stop() {
		lastCall = "stop()";
		logger.debug(lastCall);
		owner.clearCommand(LuchadorRunner.COMMAND_MOVE);
	}

	/* (non-Javadoc)
	 * @see com.robolucha.runner.luchador.lua.ScriptFacade#reset()
	 */
	@Override
	public void reset() {
		lastCall = "reset()";
		logger.debug(lastCall);
		owner.clearAllCommands();
	}

	/* (non-Javadoc)
	 * @see com.robolucha.runner.luchador.lua.ScriptFacade#turn(int)
	 */
	@Override
	public void turn(int amount) {
		lastCall = "turn(" + amount + ")";
		logger.debug(lastCall);
		owner.addTurn(codeName, amount);
	}

	/* (non-Javadoc)
	 * @see com.robolucha.runner.luchador.lua.ScriptFacade#turnGun(int)
	 */
	@Override
	public void turnGun(int amount) {
		lastCall = "turnGun(" + amount + ")";
		logger.debug(lastCall);
		owner.addTurnGun(codeName, amount);
	}

	/* (non-Javadoc)
	 * @see com.robolucha.runner.luchador.lua.ScriptFacade#fire(int)
	 */
	@Override
	public void fire(int amount) {
		lastCall = "fire(" + amount + ")";
		logger.debug(lastCall);
		owner.addFire(codeName, amount);
	}

	/* (non-Javadoc)
	 * @see com.robolucha.runner.luchador.lua.ScriptFacade#punch()
	 */
	@Override
	public void punch() {
		lastCall = "punch()";
		logger.debug(lastCall);
		owner.punch(codeName);
	}

	/* (non-Javadoc)
	 * @see com.robolucha.runner.luchador.lua.ScriptFacade#fix(int)
	 */
	@Override
	public void fix(int lutchador) {
		lastCall = "fix(" + lutchador + ")";
		logger.debug(lastCall);
		owner.fix(codeName, lutchador);
	}

	// TODO: restrict String size
	/* (non-Javadoc)
	 * @see com.robolucha.runner.luchador.lua.ScriptFacade#say(java.lang.String)
	 */
	@Override
	public void say(String message) {
		lastCall = "say(" + message + ")";
		logger.debug(lastCall);
		owner.say(message);
	}

	// TODO: restrict String size
	/* (non-Javadoc)
	 * @see com.robolucha.runner.luchador.lua.ScriptFacade#debug(java.lang.String)
	 */
	@Override
	public void debug(String message) {
		lastCall = "debug(" + message + ")";
		logger.debug(lastCall);
		owner.onMessage(MessageVO.DEBUG, message);
	}

	// TODO: restrict String size
	/* (non-Javadoc)
	 * @see com.robolucha.runner.luchador.lua.ScriptFacade#warning(java.lang.String)
	 */
	@Override
	public void warning(String message) {
		lastCall = "warning(" + message + ")";
		logger.debug(lastCall);
		owner.onMessage(MessageVO.WARNING, message);
	}

	// TODO: restrict String size
	/* (non-Javadoc)
	 * @see com.robolucha.runner.luchador.lua.ScriptFacade#danger(java.lang.String)
	 */
	@Override
	public void danger(String message) {
		lastCall = "danger(" + message + ")";
		logger.debug(lastCall);
		owner.onMessage(MessageVO.DANGER, message);
	}

	/* (non-Javadoc)
	 * @see com.robolucha.runner.luchador.lua.ScriptFacade#getLastCall()
	 */
	@Override
	public String getLastCall() {
		return lastCall;
	}

}
