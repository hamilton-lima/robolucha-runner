package com.robolucha.game.action;

import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import com.robolucha.runner.code.MethodNames;
import com.robolucha.runner.luchador.LuchadorRunner;

/**
  * @author hamiltonlima
 *
 */
public class RepeatAction implements GameAction {

	private static Logger logger = Logger.getLogger(RepeatAction.class);
	
	public void run(LinkedHashMap<Integer, LuchadorRunner> runners,
			LuchadorRunner runner) throws Exception {
		
		logger.debug( getName() + ", lutchador=" + runner.getGameComponent().getName());
		runner.run(getName());
	}

	public String getName() {
		return MethodNames.ON_REPEAT;
	}

}
