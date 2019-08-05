package com.robolucha.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.test.MockMatchRunner;

import io.swagger.client.model.ModelGameComponent;

public class FireAmountTest {

	@Test
	public void testFireAmount() {

		LuchadorRunner runner = new LuchadorRunner(new ModelGameComponent(), MockMatchRunner.build());

		assertEquals(1, runner.cleanUpAmount(-1));
		assertEquals(1, runner.cleanUpAmount(0));
		assertEquals(3, runner.cleanUpAmount(3));
		assertEquals(10, runner.cleanUpAmount(40));
		assertEquals(10, runner.cleanUpAmount(22222222));
	}

}
