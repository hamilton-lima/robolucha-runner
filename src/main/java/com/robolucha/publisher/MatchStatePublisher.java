package com.robolucha.publisher;

import java.util.Collections;
import java.util.Iterator;

import com.robolucha.game.vo.BulletVO;
import com.robolucha.game.vo.MatchRunStateVO;
import com.robolucha.game.vo.PunchVO;
import com.robolucha.models.Bullet;
import com.robolucha.models.LuchadorPublicState;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.Punch;
import com.robolucha.runner.luchador.LuchadorRunner;

import io.swagger.client.model.MainMatch;

public class MatchStatePublisher {

	private final RemoteQueue publisher;
	private String channel;

	public MatchStatePublisher(MainMatch match, RemoteQueue publisher) {
		channel = String.format("match.%s.state", match.getId());
		this.publisher = publisher;
	}

	public void update(MatchRunner matchRunner) throws Exception {
		MatchRunStateVO vo = new MatchRunStateVO();

		Iterator<Integer> iterator = matchRunner.getRunners().keySet().iterator();
		while (iterator.hasNext()) {
			Object key = (Object) iterator.next();
			LuchadorRunner runner = matchRunner.getRunners().get(key);

			// add score to the list
			if (runner != null) {
				vo.scores.add(runner.getScoreVO());

				if (runner.isActive()) {
					// read data to update the state
					String name = runner.getGameComponent().getName();
					LuchadorPublicState publicState = runner.getState().getPublicState();

					// update luchador data
					vo.luchadores.add(publicState);
				}
			}

		}

		Collections.sort(vo.scores);

		// bullets
		int pos = 0;
		while (pos < matchRunner.getBullets().size()) {
			Bullet bullet = (Bullet) matchRunner.getBullets().get(pos++);
			if (bullet == null) {
				continue;
			}

			BulletVO foo = new BulletVO();
			foo.id = bullet.getId();
			foo.owner = bullet.getOwner().getId();
			foo.x = (int)bullet.getX();
			foo.y = (int)bullet.getY();
			foo.amount = bullet.getAmount();

			vo.bullets.add(foo);
		}

		// punches
		pos = 0;
		while (pos < matchRunner.getPunches().size()) {
			Punch punch = (Punch) matchRunner.getPunches().get(pos++);
			if (punch == null) {
				continue;
			}

			PunchVO foo = new PunchVO();
			foo.x = punch.getX();
			foo.y = punch.getY();

			vo.punches.add(foo);
		}

		vo.clock = matchRunner.getGameDefinition().getDuration() - matchRunner.getTimeElapsed();
		publish(vo);

	}

	public void publish(MatchRunStateVO state) {
		MessageEnvelope envelope = MessageEnvelope.buildMatchState(state);
		publisher.publish(channel, envelope);
	}

}
