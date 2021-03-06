package com.robolucha.publisher;

import static junit.framework.TestCase.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.robolucha.game.vo.MessageVO;
import com.robolucha.runner.MatchRunner;
import com.robolucha.test.MockMatchRunner;

public class MatchMessagePublisherTest {
    MockRemoteQueue queue = new MockRemoteQueue();
    MatchMessagePublisher publisher;

    @Before
    public void setup() {
        publisher = new MatchMessagePublisher(queue);
    }

    @Test
    public void subscribeAndAccept() {
        MatchRunner runner = MockMatchRunner.build();
        runner.addListener(publisher);
        Integer id = 1;
        String type = "type1";
        String event = "event1";
        String message = "message1";
        runner.getOnMessage().onNext(new MessageVO(1, type, event, message));

        MessageVO sentToQueue = (MessageVO) ((MessageEnvelope) queue.lastPublished).message;
        assertEquals(id, sentToQueue.luchadorID);
        assertEquals(type, sentToQueue.type);
               assertEquals(event, sentToQueue.event);
        assertEquals(message, sentToQueue.message);
    }

    @Test
    public void disposeAndIsDisposed() {
        MatchRunner runner = MockMatchRunner.build();
        runner.addListener(publisher);

        assertEquals( false, publisher.isDisposed() );
        runner.cleanup();
        assertEquals( true, publisher.isDisposed() );
    }

}