package com.robolucha.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.shared.Calc;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelGameDefinition;
import io.swagger.client.model.ModelSceneComponent;

public class CalcTest {

	private static Logger logger = Logger.getLogger(CalcTest.class);

	@Test
	public void testFixAngle() {

		double[][] testData = { { 10, 10 }, { 0, 0 }, { 360, 360 }, { 370, 10 }, { -10, 350 } };

		for (int i = 0; i < testData.length; i++) {
			double[] test = testData[i];
			double result = Calc.fixAngle(test[0]);
			logger.debug(String.format("testando Calc.fixAngle(%s)=%s esperado=%s", test[0], result, test[1]));
			assertEquals(test[1], result, 0.01);
		}
	}

	@Test
	public void testIntersectRobot() throws Exception {

		MatchRunner match = MockMatchRunner.build();

		ModelGameComponent a = MockLuchador.build(1);
		ModelGameComponent b = MockLuchador.build(2);

		LuchadorRunner runnerA = match.add(a, 0).blockingFirst();
		LuchadorRunner runnerB = match.add(b, 0).blockingFirst();

		runnerA.getState().setX(100);
		runnerA.getState().setY(100);

		double newX = runnerA.getState().getX() + (2 * runnerA.getSize()) + 1;

		runnerB.getState().setX(newX);
		runnerB.getState().setY(100);
		runnerB.getState().setAngle(180);

		logger.debug("--- A : " + runnerA.getState());
		logger.debug("--- B : " + runnerB.getState());

		// colide
		assertTrue(Calc.intersectRobot(newX, 100, runnerA, runnerB));

		// menos 1 nao colide
		assertFalse(Calc.intersectRobot(newX - runnerA.getSize() - 1, 100, runnerA, runnerB));
	}

	@Test
	public void testIntersectSceneComponentWithLuchadorINSIDE() throws Exception {

		ModelGameDefinition gd = MockMatchRunner.buildGameDefinition();
		double radius = gd.getLuchadorSize() / 2;
		ModelSceneComponent sc = new ModelSceneComponent();
		sc.setX(50);
		sc.setY(50);
		sc.setWidth(200);
		sc.setHeight(300);
		sc.setLength(300);

		assertTrue(Calc.intersectCirclewithSceneComponent(50, 50, radius, sc));
	}

	@Test
	public void testIntersectSceneComponentWithLuchadorOUTSIDE() throws Exception {

		ModelGameDefinition gd = MockMatchRunner.buildGameDefinition();
		double radius = gd.getLuchadorSize() / 2;

		ModelSceneComponent sc = new ModelSceneComponent();
		sc.setX(50);
		sc.setY(50);
		sc.setWidth(200);
		sc.setHeight(100);
		sc.setLength(100);

		int x = (int) (sc.getX() + sc.getWidth() + radius + 1);
		assertFalse(Calc.intersectCirclewithSceneComponent(x, 50, radius, sc));
	}

	@Test
	public void testIntersectSceneComponentWithLuchadorTOUCHING() throws Exception {

		ModelGameDefinition gd = MockMatchRunner.buildGameDefinition();
		double radius = gd.getLuchadorSize() / 2;

		ModelSceneComponent sc = new ModelSceneComponent();
		sc.setX(50);
		sc.setY(50);
		sc.setWidth(200);
		sc.setHeight(100);
		sc.setLength(100);

		int x = (int) (sc.getX() + sc.getWidth() + radius );
		assertFalse(Calc.intersectCirclewithSceneComponent(x, 50, radius, sc));
	}

}
