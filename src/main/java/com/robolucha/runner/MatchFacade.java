package com.robolucha.runner;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.robolucha.runner.code.MatchScriptFacade;
import com.robolucha.runner.luchador.LuchadorRunner;

public class MatchFacade implements MatchScriptFacade {
	private static Logger logger = Logger.getLogger(MatchFacade.class);

	private MatchRunner runner;
	private Map<Integer, Long> lastDamageTime; 

	public MatchFacade(MatchRunner runner) {
		this.runner = runner;
		this.lastDamageTime = new HashMap<Integer, Long>();
	}

	@Override
	public void addDamage(int luchador, int amount) {
		logger.info("Add damage to :" + luchador + " amount:" + amount);
		LuchadorRunner luchadorRunner = runner.runners.get(luchador);
		
		if (luchadorRunner != null && itsTimeToHitAgain(luchador)) {
			saveLastHit(luchador);
			logger.info("Found Luchador with life:" + luchadorRunner.getState().getLife());
			luchadorRunner.damage(amount);
		}

	}

	void saveLastHit(int luchador) {
		lastDamageTime.put(luchador, System.currentTimeMillis());
	}

	boolean itsTimeToHitAgain(int luchador) {
		Long last = lastDamageTime.get(luchador);
		if( last != null ) {
			long elapsed = System.currentTimeMillis() - last;
			return elapsed > 1000;
		} 
		return true;
	}

	@Override
	public void endGame() {
		logger.error("Goodbye cruel world.");
		runner.kill();
	}

}
