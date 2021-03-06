package com.robolucha.game.action;

import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.shared.JSONFormat;

public class RemoveDeadAction implements GameAction {

	private static Logger logger = Logger.getLogger(RemoveDeadAction.class);

	public RemoveDeadAction(MatchRunner matchRunner) {
	}

	public void run(LinkedHashMap<Integer, LuchadorRunner> runners, LuchadorRunner runner) {

		logger.debug("RemoveDeadAction, lutchador=" + JSONFormat.clean(runner.getGameComponent().toString()));

		if (runner.isActive()) {
			if (runner.getState().getLife() <= 0) {
				runner.kill();
				logger.debug("RemoveDeadAction, KILLED");
			}
		}
	}

	public String getName() {
		return "removeDead";
	}
}
