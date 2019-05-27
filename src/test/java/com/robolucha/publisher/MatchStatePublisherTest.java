package com.robolucha.publisher;

import static junit.framework.TestCase.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.robolucha.game.vo.MatchRunStateVO;
import com.robolucha.models.LuchadorPublicState;
import com.robolucha.models.Match;
import com.robolucha.runner.MatchRunner;
import com.robolucha.test.MockMatchRunner;

public class MatchStatePublisherTest {

	MockRemoteQueue queue = new MockRemoteQueue();
	Match match = new Match();
	MatchStatePublisher publisher;

	@Before
	public void setup() {
		publisher = new MatchStatePublisher(match, queue);
	}

	@Test
	public void update() throws Exception {
		MatchRunner runner = MockMatchRunner.build();
		MatchRunStateVO vo = new MatchRunStateVO();
		long expected = runner.getGameDefinition().getDuration() - runner.getTimeElapsed();
		publisher.update(runner);
		MessageEnvelope envelope = (MessageEnvelope) queue.lastPublished;
		MatchRunStateVO sentToQueue = (MatchRunStateVO) envelope.message;
		assertEquals(expected, sentToQueue.clock);
	}

	@Test
	public void publish() throws Exception {
		MatchRunner runner = MockMatchRunner.build();
		MatchRunStateVO vo = new MatchRunStateVO();
		vo.clock = 42;
		long expected = 42;

		publisher.publish(vo);
		MessageEnvelope envelope = (MessageEnvelope) queue.lastPublished;
		MatchRunStateVO sentToQueue = (MatchRunStateVO) envelope.message;
		assertEquals(expected, sentToQueue.clock);
	}

	@Test
	public void publishScore() throws Exception {
		MatchRunner runner = MockMatchRunner.build();
		MatchRunStateVO vo = new MatchRunStateVO();
		vo.clock = 42;
		long expected = 42;

		LuchadorPublicState publicState1 = new LuchadorPublicState();
		publicState1.k = 1;

		LuchadorPublicState publicState2 = new LuchadorPublicState();
		publicState2.d = 1;

		ArrayList<LuchadorPublicState> states = new ArrayList<LuchadorPublicState>();
		states.add(publicState1);
		states.add(publicState2);
		vo.luchadores = states;

		publisher.publish(vo);
		MessageEnvelope envelope = (MessageEnvelope) queue.lastPublished;
		MatchRunStateVO sentToQueue = (MatchRunStateVO) envelope.message;
		assertEquals(expected, sentToQueue.clock);

		int found = 0;
		for (LuchadorPublicState state : sentToQueue.luchadores) {
			if (state.name.equals("one")) {
				found++;
				assertEquals(1, state.k);
				assertEquals(0, state.d);
			}

			if (state.name.equals("two")) {
				found++;
				assertEquals(0, state.k);
				assertEquals(1, state.d);
			}
		}

		assertEquals(2, found);
	}
}