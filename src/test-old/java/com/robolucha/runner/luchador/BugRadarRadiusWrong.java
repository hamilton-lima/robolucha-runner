package com.robolucha.runner.luchador;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.game.event.LuchadorEvent;
import com.robolucha.game.event.LuchadorEventListener;
import com.robolucha.game.event.OnFoundEvent;
import com.robolucha.game.vo.LuchadorPublicStateVO;
import com.robolucha.models.LuchadorPublicState;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.reactivex.functions.Consumer;
import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelMatch;

public class BugRadarRadiusWrong {

	private static Logger logger = Logger.getLogger(BugRadarRadiusWrong.class);

	LuchadorPublicState finalState = null;
	protected int defaultLife;
	protected int bodyCount;
	public Object lastPublished;
	LuchadorPublicStateVO found = null;
	public int notFoundCounter;
	int foundCount = 0;

	@Before
	public void setUp() throws Exception {
		foundCount = 0;
	}

	@Test
	public void testRun() throws Exception {
		logger.setLevel(Level.DEBUG);
		MatchRunner match = MockMatchRunner.build(1000);
		match.getGameDefinition().setMinParticipants(2);
		ModelGameComponent a = MockLuchador.build(1, MethodNames.ON_REPEAT, "--turnGun(90)");
		ModelGameComponent b = MockLuchador.build(-2, MethodNames.ON_REPEAT, "--turnGun(90)");

		match.add(a);
		match.add(b);

		// set initial position for luchador
		match.getOnMatchStart().subscribe(new Consumer<ModelMatch>() {
			public void accept(ModelMatch arg0) throws Exception {

				LuchadorRunner runnerA = match.getRunners().get(1L);
				LuchadorRunner runnerB = match.getRunners().get(-2L);
				runnerA.cleanUpStateAtTheEnd = false;
				runnerB.cleanUpStateAtTheEnd = false;

				defaultLife = match.getGameDefinition().getLife();

				runnerA.getState().setGunAngle(0);
				runnerA.getState().setX(100);
				runnerA.getState().setY(100);
				
				int closeEnough = (int) (runnerA.getState().getX() + match.getGameDefinition().getRadarRadius() - 1);

				runnerB.getState().setGunAngle(180);
				runnerB.getState().setX(closeEnough);
				runnerB.getState().setY(100);

				logger.debug(">> luchador A: " + runnerA.getState().getPublicState());
				logger.debug(">> luchador B: " + runnerB.getState().getPublicState());
			}

		});

		match.addListener(new LuchadorEventListener() {
			public void listen(LuchadorEvent event) {
				logger.info(">>> event : " + event);
				if (event instanceof OnFoundEvent) {
					logger.debug("FOUND OTHER! " + event);
					foundCount++;
				}
			}
		});

		MockMatchRunner.start(match);

		match.getOnMatchEnd().blockingSubscribe(new Consumer<ModelMatch>() {
			public void accept(ModelMatch arg0) throws Exception {
				logger.debug("END! foundCount = " + foundCount);
				assertTrue("onFound event was triggered " + foundCount, foundCount > 0);
			}
		});

		logger.info("START >>>");
	}

}
