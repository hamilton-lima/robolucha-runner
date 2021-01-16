package com.robolucha.game.action;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.robolucha.models.Bullet;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelTeamDefinition;

public class CheckBulletHitActionTest {

	@Test
	public void testRunFriendlyFireON() throws Exception {
		ModelTeamDefinition teamDefinition = new ModelTeamDefinition();
		teamDefinition.setId(1);
		teamDefinition.friendlyFire(false);

		MatchRunner runner = MockMatchRunner.build();
		runner.getGameDefinition().setMinParticipants(2);
		runner.getGameDefinition().setTeamDefinition(teamDefinition);

		ModelGameComponent a = MockLuchador.build(1, MethodNames.ON_REPEAT, "--");
		ModelGameComponent b = MockLuchador.build(2, MethodNames.ON_REPEAT, "--");

		LuchadorRunner aRunner = runner.add(a, 1).blockingFirst();
		LuchadorRunner bRunner = runner.add(b, 1).blockingFirst();

		Bullet bullet = new Bullet(runner.getGameDefinition().getBuletSpeed().doubleValue(),
				runner.getGameDefinition().getBulletSize(), 
				aRunner, 10, bRunner.getState().x, bRunner.getState().y,
				aRunner.getState().getGunAngle());

		int life = (int) bRunner.getState().getLife();

		CheckBulletHitAction toTest = new CheckBulletHitAction(runner, bullet);
		toTest.run(runner.getRunners(), bRunner);

		assertTrue("Life didnt change", life == (int) bRunner.getState().getLife());
	}

	@Test
	public void testRunFriendlyFireOFF() throws Exception {
		ModelTeamDefinition teamDefinition = new ModelTeamDefinition();
		teamDefinition.setId(1);
		teamDefinition.friendlyFire(true);

		MatchRunner runner = MockMatchRunner.build();
		runner.getGameDefinition().setMinParticipants(2);
		runner.getGameDefinition().setTeamDefinition(teamDefinition);

		ModelGameComponent a = MockLuchador.build(1, MethodNames.ON_REPEAT, "--");
		ModelGameComponent b = MockLuchador.build(2, MethodNames.ON_REPEAT, "--");

		LuchadorRunner aRunner = runner.add(a, 1).blockingFirst();
		LuchadorRunner bRunner = runner.add(b, 1).blockingFirst();

		Bullet bullet = new Bullet(runner.getGameDefinition().getBuletSpeed().doubleValue(),
				runner.getGameDefinition().getBulletSize(), 
				aRunner, 10, bRunner.getState().x, bRunner.getState().y,
				aRunner.getState().getGunAngle());

		int life = (int) bRunner.getState().getLife();

		CheckBulletHitAction toTest = new CheckBulletHitAction(runner, bullet);
		toTest.run(runner.getRunners(), bRunner);

		assertTrue("Life changed", life != (int) bRunner.getState().getLife());
	}

}
