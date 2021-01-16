package com.robolucha.runner;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.robolucha.game.event.OnGotDamageEvent;
import com.robolucha.models.LuchadorMatchState;
import com.robolucha.models.LuchadorPublicState;
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
		LuchadorRunner luchadorRunner = runner.getRunners().get(luchador);

		if (luchadorRunner != null && itsTimeToHitAgain(luchador)) {
			saveLastHit(luchador);
			logger.info("Found Luchador with life:" + luchadorRunner.getState().getLife());

			// TODO: when calling addDamage add the source of damage
			LuchadorPublicState source = new LuchadorPublicState();
			LuchadorMatchState sourceMatch = new LuchadorMatchState(true);
			LuchadorPublicState target = luchadorRunner.getState().getPublicState();

			luchadorRunner.damage(amount);
			luchadorRunner.addEvent(new OnGotDamageEvent(source, target, amount));
			runner.getEventHandler().damage(sourceMatch, luchadorRunner.getState(), amount);

		}

	}

	void saveLastHit(int luchador) {
		lastDamageTime.put(luchador, System.currentTimeMillis());
	}

	boolean itsTimeToHitAgain(int luchador) {
		Long last = lastDamageTime.get(luchador);
		if (last != null) {
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
