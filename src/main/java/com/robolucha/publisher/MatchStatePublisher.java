package com.robolucha.publisher;

import java.util.Collections;
import java.util.Iterator;

import com.robolucha.game.vo.BulletVO;
import com.robolucha.game.vo.MatchRunStateVO;
import com.robolucha.game.vo.PunchVO;
import com.robolucha.game.vo.SceneComponentVO;
import com.robolucha.models.Bullet;
import com.robolucha.models.LuchadorPublicState;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.Punch;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.shared.Calc;

import io.swagger.client.model.ModelMatch;
import io.swagger.client.model.ModelSceneComponent;

public class MatchStatePublisher {

	private final RemoteQueue publisher;
	private String channel;
	private String serverID;

	public MatchStatePublisher(String serverID, ModelMatch match, RemoteQueue publisher) {
		channel = String.format("match.%s.state", match.getId());
		this.publisher = publisher;
		this.serverID = serverID;
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

		Iterator<ModelSceneComponent> iteratorComponent = matchRunner.getSceneComponents().iterator();
		while (iteratorComponent.hasNext()) {
			ModelSceneComponent component = iteratorComponent.next();
			SceneComponentVO send = new SceneComponentVO();
			
			send.id = component.getId().intValue();
			send.x = component.getX().intValue();
			send.y = component.getY().intValue();
			send.width = component.getWidth().intValue();
			send.height = component.getHeight().intValue();
			send.rotation = component.getRotation().intValue();
			send.type = component.getType();
			send.color = component.getColor();
			send.alpha = Calc.roundTo4Decimals(component.getAlpha().doubleValue());
			vo.sceneComponents.add(send);
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
			foo.x = (int) bullet.getX();
			foo.y = (int) bullet.getY();
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

		if( matchRunner.isAlive() ) {
			if( matchRunner.getGameDefinition().getDuration() > 0 ) {
				vo.clock = matchRunner.getGameDefinition().getDuration() - matchRunner.getTimeElapsed();
			} else {
				// if no limit is set the clock counts the elapsed time
				vo.clock = matchRunner.getTimeElapsed();
			}
		} else {
			vo.clock = -1;
		}
		
		publish(vo);

	}

	public void publish(MatchRunStateVO state) {
		MessageEnvelope envelope = MessageEnvelope.buildMatchState(state);
		envelope.setSender(serverID);
		publisher.publish(channel, envelope);
	}

}
