package com.robolucha.publisher;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.event.ConstEvents;
import com.robolucha.models.LuchadorMatchState;
import com.robolucha.models.MatchEvent;
import com.robolucha.monitor.ThreadMonitor;
import com.robolucha.runner.MatchRunner;
import com.robolucha.shared.JSONFormat;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.swagger.client.model.ModelMatch;

public class MatchEventPublisherTest {

	private static Logger logger = Logger.getLogger(MatchEventPublisherTest.class);

	private static class MockThreadMonitor extends ThreadMonitor {

		public String lastAlive;

		@Override
		public void alive(String threadName) {
			this.lastAlive = threadName;
		}
	}

	MatchEventPublisher publisher;
	MockThreadMonitor threadMonitor;
	MatchRunner runner;
	MockRemoteQueue queue;
	Integer id = 42;
	Long start;

	@Before
	public void setUp() throws Exception {
		queue = new MockRemoteQueue();
		threadMonitor = new MockThreadMonitor();

		publisher = new MatchEventPublisher(new ModelMatch(), queue, threadMonitor);
		runner = MockMatchRunner.build();
		runner.getMatch().setId(id);
		logger.debug("match " + runner.getMatch());
		start = System.currentTimeMillis();
	}

	@Test
	public void onInit() {
		publisher.onInit(runner);
		MessageEnvelope envelope = (MessageEnvelope) queue.lastPublished;
		MatchEvent event = (MatchEvent) envelope.message;
		logger.debug("last published " + event);

		assertTrue(event.getTimeStart() >= start);
		assertEquals(id, event.getMatch().getId());
		assertEquals(ConstEvents.INIT, event.getEvent());
		assertEquals(0L, event.getComponentA());
		assertEquals(0L, event.getComponentB());
		assertEquals(0.0, event.getAmount());
	}

	@Test
	public void onStart() {
		publisher.onStart(runner);
		MessageEnvelope envelope = (MessageEnvelope) queue.lastPublished;
		MatchEvent event = (MatchEvent) envelope.message;
		logger.debug("last published " + event);

		assertTrue(event.getTimeStart() >= start);
		assertEquals(id, event.getMatch().getId());
		assertEquals(ConstEvents.START, event.getEvent());
		assertEquals(0L, event.getComponentA());
		assertEquals(0L, event.getComponentB());
		assertEquals(0.0, event.getAmount());
	}

	@Test
	public void onEnd() {
		publisher.onEnd(runner);
		MessageEnvelope envelope = (MessageEnvelope) queue.lastPublished;
		MatchEvent event = (MatchEvent) envelope.message;
		logger.debug("last published " + event);

		assertTrue(event.getTimeStart() >= start);
		assertEquals(id, event.getMatch().getId());
		assertEquals(ConstEvents.END, event.getEvent());
		assertEquals(0L, event.getComponentA());
		assertEquals(0L, event.getComponentB());
		assertEquals(0.0, event.getAmount());
	}

	@Test
	public void onAlive() {
		publisher.onAlive(runner);
		assertTrue(JSONFormat.date2timestamp(runner.getMatch().getLastTimeAlive()) >= start);
		assertEquals(runner.getThreadName(), threadMonitor.lastAlive);
	}

	@Test
	public void onKill() {
		LuchadorMatchState luchador1 = MockLuchador.buildLuchadorMatchState(1L);
		LuchadorMatchState luchador2 = MockLuchador.buildLuchadorMatchState(2L);

		publisher.onKill(runner, luchador1, luchador2);
		MessageEnvelope envelope = (MessageEnvelope) queue.lastPublished;
		MatchEvent event = (MatchEvent) envelope.message;
		logger.debug("last published " + event);

		assertTrue(event.getTimeStart() >= start);
		assertEquals(id, event.getMatch().getId());

		assertEquals(ConstEvents.KILL, event.getEvent());
		assertEquals(1L, event.getComponentA());
		assertEquals(2L, event.getComponentB());
		assertEquals(0.0, event.getAmount());
	}

	@Test
	public void onDamage() {
		LuchadorMatchState luchador1 = MockLuchador.buildLuchadorMatchState(1L);
		LuchadorMatchState luchador2 = MockLuchador.buildLuchadorMatchState(2L);

		publisher.onDamage(runner, luchador2, luchador1, 54.3);
		MessageEnvelope envelope = (MessageEnvelope) queue.lastPublished;
		MatchEvent event = (MatchEvent) envelope.message;
		logger.debug("last published " + event);

		assertTrue(event.getTimeStart() >= start);
		assertEquals(id, event.getMatch().getId());

		assertEquals(ConstEvents.DAMAGE, event.getEvent());
		assertEquals(2L, event.getComponentA());
		assertEquals(1L, event.getComponentB());
		assertEquals(54.3, event.getAmount());
	}
}