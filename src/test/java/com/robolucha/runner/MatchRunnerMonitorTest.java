package com.robolucha.runner;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.robolucha.monitor.ServerMonitor;

import io.swagger.client.model.MainMatchMetric;

public class MatchRunnerMonitorTest {

	private class MockServerMonitor extends ServerMonitor {

		public MainMatchMetric lastMetric;

		public MockServerMonitor() {
			super(null);
		}

		@Override
		public void matchMetric(MainMatchMetric metric) {
			this.lastMetric = metric;
		}

	}

	@Test
	public void testTick() throws InterruptedException {
		int interval = 1000;
		MockServerMonitor serverMonitor = new MockServerMonitor();
		MainMatchMetric metric = new MainMatchMetric();
		
		metric.setGameDefinitionID(42);
		MatchRunnerMonitor monitor = new MatchRunnerMonitor(serverMonitor, metric, interval);
		monitor.tick();
		monitor.tick();
		monitor.addPlayer();
		monitor.addPlayer();

		Thread.sleep(interval +1);
		monitor.tick();

		assertEquals(42, serverMonitor.lastMetric.getGameDefinitionID().intValue());
		assertEquals(3, serverMonitor.lastMetric.getFps().intValue());
		assertEquals(2, serverMonitor.lastMetric.getPlayers().intValue());
	}

	@Test
	public void testKeepNumberOfPlayersAfterTick() throws InterruptedException {
		int interval = 200;
		MockServerMonitor serverMonitor = new MockServerMonitor();
		MainMatchMetric metric = new MainMatchMetric();
		
		metric.setGameDefinitionID(42);
		MatchRunnerMonitor monitor = new MatchRunnerMonitor(serverMonitor, metric, interval);
		monitor.tick();
		monitor.tick();
		monitor.addPlayer();
		monitor.addPlayer();

		Thread.sleep(interval +1);
		monitor.tick();
		assertEquals(2, serverMonitor.lastMetric.getPlayers().intValue());

		Thread.sleep(interval +1);
		monitor.tick();
		assertEquals(2, serverMonitor.lastMetric.getPlayers().intValue());
	}

}
