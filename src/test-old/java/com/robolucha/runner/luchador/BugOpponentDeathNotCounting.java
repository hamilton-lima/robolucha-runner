package com.robolucha.runner.luchador;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.game.event.MatchEventListener;
import com.robolucha.game.vo.MatchRunStateVO;
import com.robolucha.models.LuchadorMatchState;
import com.robolucha.models.LuchadorPublicState;
import com.robolucha.models.ScoreVO;
import com.robolucha.publisher.MessageEnvelope;
import com.robolucha.publisher.MockRemoteQueue;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.reactivex.functions.Consumer;
import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelMatch;

/**
 * This test creates 2 luchadores A and B - A need to be a the left of B with
 * the gun pointed and will fire(10) - B needs to have life = 1 to make sure the
 * hit kills it - expect B deaths and A kills to increase
 * 
 * @author hamilton
 * @since 2019-04-27
 * @see https://robolucha.atlassian.net/browse/RB-59
 */
public class BugOpponentDeathNotCounting {

	private static Logger logger = Logger.getLogger(BugOpponentDeathNotCounting.class);

	int bodyCount;
	LuchadorPublicState finalStateA = null;
	LuchadorPublicState finalStateB = null;

	ScoreVO finalScoreA = null;
	ScoreVO finalScoreB = null;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRun() throws Exception {

		MockRemoteQueue queue = new MockRemoteQueue();
		MatchRunner match = MockMatchRunner.build(3000, queue);
		match.getGameDefinition().setMinParticipants(2);

		ModelGameComponent a = MockLuchador.build(1, MethodNames.ON_START, "fire(5)");
		ModelGameComponent b = MockLuchador.build(2, MethodNames.ON_REPEAT, "turn(90)");

		match.add(a);
		match.add(b);

		// set initial position for luchador
		match.getOnMatchStart().subscribe(new Consumer<ModelMatch>() {
			public void accept(ModelMatch arg0) throws Exception {

				LuchadorRunner runnerA = match.getRunners().get(1L);
				LuchadorRunner runnerB = match.getRunners().get(2L);
				runnerA.cleanUpStateAtTheEnd = false;
				runnerB.cleanUpStateAtTheEnd = false;

				runnerA.getState().setAngle(0);
				runnerA.getState().setX(100);
				runnerA.getState().setY(100);

				runnerB.getState().setX(200);
				runnerB.getState().setY(100);
				runnerB.getState().setLife(1.0);
				runnerB.getState().score.setScore(1000);
//				runnerB.getGameComponent().setRespawnCooldown(1);

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

		// get final state
		match.getOnMatchEnd().blockingSubscribe(new Consumer<ModelMatch>() {
			public void accept(ModelMatch arg0) throws Exception {
				LuchadorRunner runner = match.getRunners().get(1L);
				finalStateA = runner.getState().getPublicState();
				logger.debug(">> final state A: " + finalStateA);

				runner = match.getRunners().get(2L);
				finalStateB = runner.getState().getPublicState();
				logger.debug(">> final state B: " + finalStateB);
			}
		});

		MessageEnvelope envelope = (MessageEnvelope) queue.lastPublished;
		MatchRunStateVO publishedState = (MatchRunStateVO) envelope.message;
		publishedState.scores.forEach((score) -> {
			if (score.getId().equals(1L)) {
				finalScoreA = score;
				logger.debug(">> final score A: " + finalScoreA);
			}
			if (score.getId().equals(2L)) {
				finalScoreB = score;
				logger.debug(">> final score B: " + finalScoreB);
			}
		});

		logger.debug(">> body count: " + bodyCount);
		assertTrue("Body count > 0 ", bodyCount > 0);

		assertTrue("Luchador A PublishState.K should be > 0", finalStateA.k > 0);
		assertTrue("Luchador B PublishState.D should be > 0", finalStateB.d > 0);

		assertTrue("Luchador A Score.K should be > 0", finalScoreA.getK() > 0);
		assertTrue("Luchador B Score.D should be > 0", finalScoreB.getD() > 0);

		assertTrue("Luchador B Score.score should be = 1000", finalStateB.score == 1000);
	}

}
