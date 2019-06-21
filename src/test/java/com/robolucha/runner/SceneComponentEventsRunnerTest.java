package com.robolucha.runner;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.event.match.MatchEventVO;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.reactivex.functions.Consumer;
import io.swagger.client.model.MainCode;
import io.swagger.client.model.MainGameComponent;
import io.swagger.client.model.MainGameDefinition;
import io.swagger.client.model.MainSceneComponent;

public class SceneComponentEventsRunnerTest {

	public class MockFacade extends MatchFacade {

		int luchador;
		int amount;
		boolean endGameCalled;

		public MockFacade(MatchRunner runner) {
			super(runner);
		}

		public void addDamage(int luchador, int amount) {
			this.luchador = luchador;
			this.amount = amount;
		}

		@Override
		public void endGame() {
			this.endGameCalled = true;
		}
	}

	private static Logger logger = Logger.getLogger(SceneComponentEventsRunnerTest.class);

	@Before
	public void setUp() throws Exception {
	}

	static double aX1;
	static MockFacade facade;

	MainSceneComponent buildSceneComponent() {
		MainSceneComponent result = new MainSceneComponent();
		result.setId(42);
		result.setX(100);
		result.setY(100);
		result.setWidth(100);
		result.setHeight(100);

		MainCode code = new MainCode();
		code.setEvent("onHitOther");
		code.setScript("endGame()");
		result.setCodes(new ArrayList<MainCode>());
		result.getCodes().add(code);

		result.setColider(true);
		result.setBlockMovement(false);

		return result;
	}

	@Test
	public void testRun() throws Exception {

		MainGameDefinition gameDefinition = MockMatchRunner.buildGameDefinition();
		gameDefinition.setDuration(1000);
		gameDefinition.setMinParticipants(1);
		gameDefinition.getSceneComponents().add(buildSceneComponent());

		MatchRunner match = MockMatchRunner.build(gameDefinition);
		MainGameComponent a = MockLuchador.build(1, MethodNames.ON_REPEAT, "move(10)");
		LuchadorRunner runnerA = match.add(a).blockingFirst();
		facade = new MockFacade(match);
		match.eventsRunner.facade = facade;

		match.getOnMatchStart().subscribe(new Consumer<MatchEventVO>() {
			public void accept(MatchEventVO arg0) throws Exception {

				int shift = match.getGameDefinition().getLuchadorSize() / 2;
				aX1 = 100 - shift - 2;
				runnerA.getState().setX(aX1);
				runnerA.getState().setY(120);

				logger.debug("--- A : " + runnerA.getState().getPublicState());
			}
		});

		MockMatchRunner.start(match);

		match.getOnMatchEnd().blockingSubscribe();

		logger.debug("--- A after : " + runnerA);

		double aX2 = runnerA.getState().getPublicState().x;
		logger.debug(String.format("*** results : a[%s, %s]", aX1, aX2));
		assertTrue("check if luchador moved", aX2 > aX1);
		assertTrue("check if endGame code was called", facade.endGameCalled);
	}
	
}
