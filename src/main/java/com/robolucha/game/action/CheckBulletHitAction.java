package com.robolucha.game.action;

import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import com.robolucha.game.event.OnGotDamageEvent;
import com.robolucha.models.Bullet;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.shared.Calc;

public class CheckBulletHitAction implements GameAction {

	private static Logger logger = Logger.getLogger(CheckBulletHitAction.class);

	private Bullet bullet;

	private MatchRunner matchRunner;

	public CheckBulletHitAction(MatchRunner matchRunner, Bullet bullet) {
		this.bullet = bullet;
		this.matchRunner = matchRunner;
	}

	public void run(LinkedHashMap<Integer, LuchadorRunner> runners, LuchadorRunner runner) {

		if (logger.isDebugEnabled()) {
			logger.debug("checkbullethit, bullet=" + bullet);
			logger.debug("checkbullethit, runner=" + runner);
		}

		if (bullet.isActive()) {

			if (bullet.getOwner().getId() != runner.getGameComponent().getId()) {

				if (logger.isDebugEnabled()) {
					logger.debug("checkbullethit:" + runner);
				}

				// the bullet hit the target
				if (Calc.intersectBullet(runner, bullet)) {

					bullet.setActive(false);

					// team definition exists
					if (matchRunner.getGameDefinition().getTeamDefinition().getId() > 0) {

						// different team 
						if (bullet.getOwner().getState().getTeam() != runner.getTeamId()) {
							logger.info("Not in the same team, set damage");
							addDamage(runner);
						} else {
							
							// same team
							logger.info("SAME team: friendlyfire? " + matchRunner.isFriendlyFire());

							if (matchRunner.isFriendlyFire()) {
								addDamage(runner);
							}
						}

					} else {
						addDamage(runner);
					}

					if (runner.getState().getLife() <= 0) {
						logger.info("player is dead!: " + runner.getGameComponent().getId());
						matchRunner.getEventHandler().kill(bullet.getOwner().getState(), runner.getState());
					}

				}
			}
		}

	}

	// Apply damage and generate events
	private void addDamage(LuchadorRunner runner) {
		if (logger.isDebugEnabled()) {
			logger.debug("checkbullethit, dano aplicado = " + bullet.getAmount());
		}

		runner.damage(bullet.getAmount());

		runner.addEvent(new OnGotDamageEvent(bullet.getOwner().getState().getPublicState(),
				runner.getState().getPublicState(), bullet.getAmount()));

		matchRunner.getEventHandler().damage(bullet.getOwner().getState(), runner.getState(), bullet.getAmount());
	}

	public String getName() {
		return "checkBulletHit";
	}

}
