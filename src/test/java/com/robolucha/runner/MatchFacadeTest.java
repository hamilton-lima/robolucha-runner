package com.robolucha.runner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MatchFacadeTest {

	@Test
	public void testSaveLastHit() throws InterruptedException {
		MatchFacade facade = new MatchFacade(null);
		facade.saveLastHit(1);
		assertFalse(facade.itsTimeToHitAgain(1));
		Thread.sleep(1001);
		assertTrue(facade.itsTimeToHitAgain(1));
	}

	@Test
	public void testItsTimeToHitAgainFirstTime() {
		MatchFacade facade = new MatchFacade(null);
		assertTrue(facade.itsTimeToHitAgain(1));
	}

}
