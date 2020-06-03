package com.robolucha.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import com.robolucha.models.LuchadorMatchState;


public class LuchadorMatchStateTest {

	@Test
	public void testIsNPC() {
		LuchadorMatchState state = new LuchadorMatchState(true);
		assertTrue(state.getPublicState().npc);

		state = new LuchadorMatchState(false);
		assertFalse(state.getPublicState().npc);
	}
	
	@Test
	public void testSetLife() {
		LuchadorMatchState state = new LuchadorMatchState(true);
		Random r = new Random();
		double randomDouble = r.nextDouble();
		state.setLife(randomDouble);
		assertEquals((int)randomDouble, state.getPublicState().life, 0.001);
	}

	@Test
	public void testSetAngle() {
		LuchadorMatchState state = new LuchadorMatchState(true);
		Random r = new Random();
		double randomDouble = r.nextDouble();
		state.setAngle(randomDouble);
		assertEquals((int)randomDouble, state.getPublicState().angle, 0.001);
	}

	@Test
	public void testSetGunAngle() {
		LuchadorMatchState state = new LuchadorMatchState(true);
		Random r = new Random();
		double randomDouble = r.nextDouble();
		state.setGunAngle(randomDouble);
		assertEquals((int)randomDouble, state.getPublicState().gunAngle, 0.001);
	}

	@Test
	public void testSetX() {
		LuchadorMatchState state = new LuchadorMatchState(true);
		Random r = new Random();
		double randomDouble = r.nextDouble();
		state.setX(randomDouble);
		assertEquals((int)randomDouble, state.getPublicState().x, 0.001);
	}

	@Test
	public void testSetY() {
		LuchadorMatchState state = new LuchadorMatchState(true);
		Random r = new Random();
		double randomDouble = r.nextDouble();
		state.setY(randomDouble);
		assertEquals((int)randomDouble, state.getPublicState().y, 0.001);
	}

}
