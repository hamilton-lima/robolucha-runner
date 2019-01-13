package com.robolucha.runner.luchador;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.event.match.MatchEventVO;
import com.robolucha.game.event.LuchadorEvent;
import com.robolucha.game.event.LuchadorEventListener;
import com.robolucha.game.event.MatchEventListener;
import com.robolucha.models.Luchador;
import com.robolucha.models.LuchadorMatchState;
import com.robolucha.models.LuchadorPublicState;
import com.robolucha.runner.MatchRunner;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.reactivex.functions.Consumer;

/**
 * This test creates 2 luchadores A and B - A need to be a the left of B with
 * the gun pointed and will fire(10) - B needs to have life = 1 to make sure the
 * hit kills it - expect B to respawn after the respawn timer
 * 
 * @author hamilton
 *
 */
public class BugRespawnNotWorking {

	private static Logger logger = Logger.getLogger(BugRespawnNotWorking.class);
	LuchadorPublicState finalState = null;
	protected int defaultLife;
	protected int bodyCount;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRun() throws Exception {

		MatchRunner match = MockMatchRunner.build(5000);
		match.getGameDefinition().setMinParticipants(2);
		Luchador a = MockLuchador.build(1L, MethodNames.ON_START, "fire(5)");
		Luchador b = MockLuchador.build(2L, MethodNames.ON_REPEAT, "turn(90)");

		match.add(a);
		match.add(b);

		// set initial position for luchador
		match.getOnMatchStart().subscribe(new Consumer<MatchEventVO>() {
			public void accept(MatchEventVO arg0) throws Exception {

				LuchadorRunner runnerA = match.getRunners().get(1L);
				LuchadorRunner runnerB = match.getRunners().get(2L);
				runnerA.cleanUpStateAtTheEnd = false;
				runnerB.cleanUpStateAtTheEnd = false;

				defaultLife = runnerB.getGameComponent().getLife();

				runnerA.getState().setAngle(0);
				runnerA.getState().setX(100);
				runnerA.getState().setY(100);

				runnerB.getState().setX(200);
				runnerB.getState().setY(100);
				runnerB.getState().setLife(1.0);
				runnerB.getGameComponent().setRespawnCooldown(1);

				logger.debug(">> luchador A: " + runnerA.getState().getPublicState());
				logger.debug(">> luchador B: " + runnerB.getState().getPublicState());
			}

		});

		match.addListener(new MatchEventListener() {

			@Override
			public void onKill(MatchRunner runner, LuchadorMatchState lutchadorA, LuchadorMatchState lutchadorB) {
				bodyCount++;
			}

			public void onStart(MatchRunner runner) {
			}

			public void onInit(MatchRunner runner) {
			}

			public void onEnd(MatchRunner runner) {
			}

			public void onDamage(MatchRunner runner, LuchadorMatchState lutchadorA, LuchadorMatchState lutchadorB,
					Double amount) {
			}

			public void onAlive(MatchRunner matchRunner) {
			}
		});

		MockMatchRunner.start(match);

		// subscribe to event when KILL happens

		// get final state
		match.getOnMatchEnd().blockingSubscribe(new Consumer<MatchEventVO>() {
			public void accept(MatchEventVO arg0) throws Exception {
				LuchadorRunner runner = match.getRunners().get(2L);
				finalState = runner.getState().getPublicState();
			}
		});

		logger.debug(">> body count: " + bodyCount);
		assertTrue("Body count > 0 ", bodyCount > 0);

		logger.debug(">> luchador B after: " + finalState);
		assertTrue("Luchador B is active again", finalState.life == defaultLife);

	}

}
