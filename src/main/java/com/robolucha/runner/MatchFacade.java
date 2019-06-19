package com.robolucha.runner;

import org.apache.log4j.Logger;

import com.robolucha.runner.code.MatchScriptFacade;
import com.robolucha.runner.luchador.LuchadorRunner;

public class MatchFacade implements MatchScriptFacade {
	private static Logger logger = Logger.getLogger(MatchFacade.class);

	private MatchRunner runner;

	public MatchFacade(MatchRunner runner) {
		this.runner = runner;
	}

	@Override
	public void addDamage(int luchador, int amount) {
		logger.info("Add damage to :" + luchador + " amount:" + amount);
		LuchadorRunner luchadorRunner = runner.runners.get(luchador);
		if (luchadorRunner != null) {
			logger.info("Found Luchador with life:" + luchadorRunner.getState().getLife());
			luchadorRunner.damage(amount);
		}

	}

	@Override
	public void endGame() {
		logger.error("Goodbye cruel world.");
		runner.kill();
	}

}
