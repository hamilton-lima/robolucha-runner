package com.robolucha.game;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.reactivex.functions.Consumer;
import io.swagger.client.model.MainGameComponent;
import io.swagger.client.model.MainMatch;

public class CheckMoveActionTest {

	private static Logger logger = Logger.getLogger(CheckMoveActionTest.class);

	@Before
	public void setUp() throws Exception {
	}

	static double aX1;
	static double bX1;

	@Test
	public void testRun() throws Exception {

		MatchRunner match = MockMatchRunner.build();
		match.getGameDefinition().setDuration(1000);

		MainGameComponent a = MockLuchador.build(1, MethodNames.ON_REPEAT, "move(10)");
		MainGameComponent b = MockLuchador.build(2, MethodNames.ON_REPEAT, "move(-10)");

		LuchadorRunner runnerA = match.add(a).blockingFirst();
		LuchadorRunner runnerB = match.add(b).blockingFirst();

		match.getOnMatchStart().subscribe(new Consumer<MainMatch>() {
			public void accept(MainMatch arg0) throws Exception {

				runnerA.getState().setX(100);
				runnerA.getState().setY(100);

				runnerB.getState().setX(100);
				runnerB.getState().setY(250);

				aX1 = runnerA.getState().getPublicState().x;
				bX1 = runnerB.getState().getPublicState().x;

				logger.debug("--- A : " + runnerA.getState().getPublicState());
				logger.debug("--- B : " + runnerB.getState().getPublicState());
			}
		});

		MockMatchRunner.start(match);

		match.getOnMatchEnd().blockingSubscribe();
		
		logger.debug("--- A depois : " + runnerA);
		logger.debug("--- B depois : " + runnerB.getState());

		double aX2 = runnerA.getState().getPublicState().x;
		double bX2 = runnerB.getState().getPublicState().x;

		logger.debug(String.format("*** resultados : a[%s, %s], b[%s, %s]", aX1, aX2, bX1, bX2));

		assertTrue("verifica se lutchador moveu A", aX2 > aX1);
		assertTrue("verifica se lutchador moveu B", bX2 < bX1);

	}
}
