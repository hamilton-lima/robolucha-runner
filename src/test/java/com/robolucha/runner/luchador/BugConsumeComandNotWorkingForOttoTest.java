package com.robolucha.runner.luchador;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.event.match.MatchEventVO;
import com.robolucha.models.LuchadorPublicState;
import com.robolucha.runner.MatchRunner;
import com.robolucha.test.MockMatchRunner;

import io.reactivex.functions.Consumer;

public class BugConsumeComandNotWorkingForOttoTest {

	private static Logger logger = Logger.getLogger(BugConsumeComandNotWorkingForOttoTest.class);
	LuchadorPublicState  otto = null;
	LuchadorPublicState  farol = null;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRun() throws Exception {

		MatchRunner match = MockMatchRunner.buildWithDefaultLuchador();

		// set initial position for luchador
		match.getOnMatchStart().subscribe(new Consumer<MatchEventVO>() {
			public void accept(MatchEventVO arg0) throws Exception {

				LuchadorRunner ottoRunner = match.getRunners().get(-1L);
				LuchadorRunner farolRunner = match.getRunners().get(-2L);

				ottoRunner.cleanUpStateAtTheEnd = false;
				farolRunner.cleanUpStateAtTheEnd = false;

				int xCloseToTheWall = match.getGameDefinition().getArenaWidth()
						- match.getGameDefinition().getLuchadorSize() - 1;

				ottoRunner.getState().setX(xCloseToTheWall);
				ottoRunner.getState().setY(100);

				farolRunner.getState().setX(100);
				farolRunner.getState().setY(300);

				logger.debug(">> otto : " + ottoRunner.getState().getPublicState());
				logger.debug(">>  farol : " + farolRunner.getState().getPublicState());
			}

		});

		MockMatchRunner.start(match);

		// get final state
		match.getOnMatchEnd().blockingSubscribe(new Consumer<MatchEventVO>() {
			public void accept(MatchEventVO arg0) throws Exception {
				otto = match.getRunners().get(-1L).getState().getPublicState();
				farol = match.getRunners().get(-2L).getState().getPublicState();
			}
		});

		logger.debug(">>> otto depois : " + otto);
		logger.debug(">>> farol depois : " + farol);

		assertTrue("Farol angle > 0", farol.angle > 0 );
		assertTrue("Otto angle > 0", otto.angle > 0 );

	}

}
