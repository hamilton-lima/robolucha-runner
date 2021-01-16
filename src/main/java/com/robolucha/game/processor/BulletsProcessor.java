package com.robolucha.game.processor;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.robolucha.game.action.CheckBulletHitAction;
import com.robolucha.models.Bullet;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.SafeList;
import com.robolucha.shared.Calc;

import io.swagger.client.model.ModelSceneComponent;

public class BulletsProcessor {

	private static Logger logger = Logger.getLogger(BulletsProcessor.class);

	public SafeList bullets;
	private MatchRunner runner;
	private int halfSize;

	public BulletsProcessor(MatchRunner runner, SafeList bullets2) {
		this.bullets = bullets2;
		this.runner = runner;
		this.halfSize = runner.getGameDefinition().getBulletSize() / 2;
	}

	public void process() {

		logger.debug("----- bullets process(), bullets.size()=" + bullets.size());

		int pos = 0;
		while (pos < bullets.size()) {
			Bullet bullet = (Bullet) bullets.get(pos++);
			if (bullet == null) {
				continue;
			}

			if (logger.isDebugEnabled()) {
				logger.debug(">>> " + bullet);
			}

			moveBullet(bullet);
			checkBulletHit(bullet);
		}

		removeDeadBullets();

	}

	public void moveBullet(Bullet bullet) {
		bullet.move(runner.getDelta());
	}

	public void checkBulletHit(Bullet bullet) {
		logger.debug("checkBulletHit " + bullet.getId() );

		CheckBulletHitAction action = new CheckBulletHitAction(runner, bullet);
		runner.runAllActive(action);

		logger.debug("checkBulletHit is active " + bullet.isActive() );

		if (bullet.isActive()) {
			checkIfHitSceneComponent(bullet);
		}
	}

	public void removeDeadBullets() {

		int pos = 0;
		while (pos < bullets.size()) {
			Bullet bullet = (Bullet) bullets.get(pos);
			if (bullet == null) {
				pos++;
				continue;
			}

			if (!bullet.isActive()) {
				bullets.remove(pos);
				pos++;
				continue;
			}

			if (!Calc.insideTheMapLimits(runner.getGameDefinition().getArenaWidth(),
					runner.getGameDefinition().getArenaHeight(), bullet.getX(), bullet.getY(), halfSize)) {
				bullets.remove(pos);
			}

			pos++;
		}

	}

	public void cleanup() {
		this.bullets = null;
		this.runner = null;
	}

	private void checkIfHitSceneComponent(Bullet bullet) {

		double radius = bullet.getSize() / 2;

		Iterator<ModelSceneComponent> sceneIterator = runner.getSceneComponents().iterator();
		logger.debug("scene components: " + runner.getSceneComponents().size() );
		
		while (sceneIterator.hasNext()) {
			ModelSceneComponent current = sceneIterator.next();

			logger.debug("component: " + current.getId() + " life: " + current.getLife() );

			if (current.getLife() > 0
					&& Calc.intersectCirclewithSceneComponent(bullet.getX(), bullet.getY(), radius, current)) {

				bullet.setActive(false);

				// Add damage to sceneComponent
				current.setLife((int) (current.getLife() - bullet.getAmount()));

				// Check if life is under ZERO and remove it 
				if (current.getLife() <= 0) {
					sceneIterator.remove();
				}
				
				logger.debug("bullet hit obstacle and removed life");

			}
		}

	}

}