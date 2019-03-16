package com.robolucha.publisher;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.robolucha.game.vo.MatchRunStateVO;
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
        MatchRunStateVO sentToQueue = (MatchRunStateVO) queue.lastPublished;
        assertEquals(expected, sentToQueue.clock);
    }

    @Test
    public void publish() throws Exception {
        MatchRunner runner = MockMatchRunner.build();
        MatchRunStateVO vo = new MatchRunStateVO();
        vo.clock = 42;
        long expected = 42;

        publisher.publish(vo);
        MatchRunStateVO sentToQueue = (MatchRunStateVO) queue.lastPublished;
        assertEquals(expected, sentToQueue.clock);
    }
}