package com.robolucha.runner.luchador;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.event.match.MatchEventVO;
import com.robolucha.runner.MatchRunner;
import com.robolucha.test.MockMatchRunner;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class BugConsumeComandNotWorkingForOttoTest {

	private static Logger logger = Logger.getLogger(BugConsumeComandNotWorkingForOttoTest.class);
	LuchadorRunner otto = null;
	LuchadorRunner farol = null;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRun() throws Exception {

		MatchRunner match = MockMatchRunner.buildWithDefaultLuchador();

		// set initial position for luchador
		match.getOnMatchStart().subscribe(new Consumer<MatchEventVO>() {
			public void accept(MatchEventVO arg0) throws Exception {

				LuchadorRunner otto = match.getRunners().get(1L);
				LuchadorRunner farol = match.getRunners().get(2L);

				otto.cleanUpStateAtTheEnd = false;
				farol.cleanUpStateAtTheEnd = false;

				int xCloseToTheWall = match.getGameDefinition().getArenaWidth()
						- match.getGameDefinition().getLuchadorSize() - 1;

				otto.getState().setX(xCloseToTheWall);
				otto.getState().setY(100);

				farol.getState().setX(100);
				farol.getState().setY(300);

				logger.debug(">> A : " + otto.getState().getPublicState());
				logger.debug(">>  B : " + farol.getState().getPublicState());
			}

		});

		MockMatchRunner.start(match);

		// get final state
		match.getOnMatchEnd().blockingSubscribe(new Consumer<MatchEventVO>() {
			public void accept(MatchEventVO arg0) throws Exception {
				otto = match.getRunners().get(1L);
				farol = match.getRunners().get(2L);
			}
		});

		logger.debug(">>> A depois : " + otto.getState().getPublicState());
		logger.debug(">>> B depois : " + farol.getState().getPublicState());

		// TODO: replace this tests with the proper turn() test 
		int dif = otto.getState().getPublicState().x - farol.getState().getPublicState().x;
		logger.debug("---diferenca do X  : " + dif);
		assertTrue("moveram o mesmo ?", dif == 0);


	}

}
