package com.robolucha.runner.luchador;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.event.match.MatchEventVO;
import com.robolucha.models.LuchadorPublicState;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.reactivex.functions.Consumer;
import io.swagger.client.model.MainGameComponent;

/**
 * This bug happens when command execute queue dont retrict the execution of
 * comands with the same name. e.g. multiple move() should be executed one after
 * another, not at the same time
 * 
 * @author hamilton
 *
 */
public class BugConsumeCommandsOfSameType {

	private static Logger logger = Logger.getLogger(BugConsumeCommandsOfSameType.class);
	LuchadorPublicState finalState = null;
	int startX = 100;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRun() throws Exception {

		MatchRunner match = MockMatchRunner.build(300);
		match.getGameDefinition().setMinParticipants(1);
		MainGameComponent a = MockLuchador.build(1, MethodNames.ON_REPEAT, "move(10) \n move(30) \n move(30)");

		match.add(a);

		// set initial position for luchador
		match.getOnMatchStart().subscribe(new Consumer<MatchEventVO>() {
			public void accept(MatchEventVO arg0) throws Exception {

				LuchadorRunner runner = match.getRunners().get(1L);
				runner.cleanUpStateAtTheEnd = false;

				// will increase x when moving
				runner.getState().setAngle(0);
				runner.getState().setX(startX);
				runner.getState().setY(100);

				logger.debug(">> luchador A: " + runner.getState().getPublicState());
			}

		});

		MockMatchRunner.start(match);

		// get final state
		match.getOnMatchEnd().blockingSubscribe(new Consumer<MatchEventVO>() {
			public void accept(MatchEventVO arg0) throws Exception {
				LuchadorRunner runner = match.getRunners().get(1L);
				finalState = runner.getState().getPublicState();
			}
		});

		logger.debug(">> luchador A after: " + finalState);
		int distance = finalState.x - startX;
		logger.debug(">> distance=" + distance);

		assertTrue("Luchador A moved at least 1", distance > 30);
		assertTrue("Luchador A moved less or equal to 10", distance <= 50);

	}

}
