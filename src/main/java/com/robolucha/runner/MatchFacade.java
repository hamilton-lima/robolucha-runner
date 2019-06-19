package com.robolucha.runner;

import org.apache.log4j.Logger;

import com.robolucha.runner.code.MatchScriptFacade;

public class MatchFacade implements MatchScriptFacade {
	private static Logger logger = Logger.getLogger(MatchFacade.class);

	private MatchRunner runner;

	public MatchFacade(MatchRunner runner) {
		this.runner = runner;
	}

	@Override
	public void addDamage(int luchador, int amount) {
		logger.error(">>>>>>> ADD DAMAGE to:" + luchador +" amount:"+ amount);
	}

	@Override
	public void endGame() {
		logger.error(">>>>>>> END MATCHHHHHHHHHHH");
	}

}
