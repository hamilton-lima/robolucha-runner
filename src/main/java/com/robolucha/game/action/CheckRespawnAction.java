package com.robolucha.game.action;

import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.RespawnPoint;
import com.robolucha.runner.luchador.LuchadorRunner;

public class CheckRespawnAction implements GameAction {

	private static Logger logger = Logger.getLogger(CheckRespawnAction.class);
	private MatchRunner matchRunner;

	public CheckRespawnAction(MatchRunner matchRunner) {
		this.matchRunner = matchRunner;
	}

	public void run(LinkedHashMap<Integer, LuchadorRunner> runners,
			LuchadorRunner runner) {

		if (logger.isDebugEnabled()) {
			logger.debug("*** " + getName() + ", luchador="
					+ runner.getGameComponent());

			logger.debug("*** " + getName() + ", isActive=" + runner.isActive());
			logger.debug("*** " + getName() + ", getLastRunningError="
					+ runner.getLastRunningError());

			logger.debug("*** " + getName() + ", getRespawnCoolDown="
					+ runner.getRespawnCoolDown());

		}

		// not active
		if (!runner.isActive()) {

			// DEAD
			if (LuchadorRunner.DEAD.equals(runner.getLastRunningError())) {

				// respawn timer is done
				if (runner.getRespawnCoolDown() <= 0) {

					RespawnPoint point = matchRunner.getRespawnPoint(runner);
					runner.respawn(point);

				}

			}

		}
	}

	public String getName() {
		return "checkrespawn";
	}

}
