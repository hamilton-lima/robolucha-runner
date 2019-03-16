package com.robolucha.runner.luchador;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.event.match.MatchEventVO;
import com.robolucha.game.event.MatchEventListener;
import com.robolucha.game.vo.LuchadorPublicStateVO;
import com.robolucha.game.vo.MatchRunStateVO;
import com.robolucha.models.Luchador;
import com.robolucha.models.LuchadorMatchState;
import com.robolucha.models.LuchadorPublicState;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.runner.MatchRunner;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;

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
	public Object lastPublished;
	LuchadorPublicStateVO found = null;
	public int notFoundCounter;

	private class RemoteQueueInspector extends RemoteQueue {

		public RemoteQueueInspector() {
		}

		@Override
		public Observable<Long> publish(String channel, Object subjectToPublish) {
			lastPublished = subjectToPublish;
			MatchRunStateVO publishedState = (MatchRunStateVO) lastPublished;
			logger.debug(">> Publishing data: luchadores.size=" + publishedState.luchadores.size());

			found = null;
			
			publishedState.luchadores.forEach((luchador) -> {
				if (luchador.state.id == 2L) {
					found = luchador;
				}
			});

			if (found == null) {
				notFoundCounter++;
			}

			return Observable.just(0L);
		}

		public <T> BehaviorSubject subscribe(String channel, Class<T> clazzToSubscribe) {
			return BehaviorSubject.create();
		}
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRun() throws Exception {

		RemoteQueueInspector queue = new RemoteQueueInspector();
		MatchRunner match = MockMatchRunner.build(3000, queue);
		match.getGameDefinition().setMinParticipants(2);

		Luchador a = MockLuchador.build(1L, MethodNames.ON_START, "fire(5)");
		Luchador b = MockLuchador.build(-2L, MethodNames.ON_REPEAT, "turn(90)");

		match.add(a);
		match.add(b);

		// set initial position for luchador
		match.getOnMatchStart().subscribe(new Consumer<MatchEventVO>() {
			public void accept(MatchEventVO arg0) throws Exception {

				LuchadorRunner runnerA = match.getRunners().get(1L);
				LuchadorRunner runnerB = match.getRunners().get(-2L);
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
				LuchadorRunner runner = match.getRunners().get(-2L);
				finalState = runner.getState().getPublicState();
			}
		});

		MatchRunStateVO publishedState = (MatchRunStateVO) lastPublished;
		publishedState.luchadores.forEach((luchador) -> {
			if (luchador.state.id == -2L) {
				found = luchador;
			}
		});

		assertTrue("Luchador B should not be found in the publishing list when is DEAD", notFoundCounter > 0);

		if (found != null) {
			logger.debug(">> luchador B found in the pusblishing data: name=" + found.name + " id=" + found.state.id);
		} else {
			logger.debug(">> luchador B NOT found in the publishing data");
		}

		assertTrue("Luchador B is part of the data to be published", found != null);

		logger.debug(">> body count: " + bodyCount);
		assertTrue("Body count > 0 ", bodyCount > 0);

		logger.debug(">> luchador B after: " + finalState);
		assertTrue("Luchador B is active again", finalState.life == defaultLife);

	}

}
