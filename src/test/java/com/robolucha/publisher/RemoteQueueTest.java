package com.robolucha.publisher;

import static junit.framework.TestCase.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.runner.Config;
import com.robolucha.test.MockLuchador;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.swagger.client.model.ModelCode;
import io.swagger.client.model.ModelGameComponent;

public class RemoteQueueTest {
    RedisDockerHelper docker = new RedisDockerHelper();
    private Logger logger = Logger.getLogger(RemoteQueueTest.class);

    @Before
    public void setUp() throws Exception {
        docker.stop();
        docker.start();
    }

    @Test
    public void publishAndSubscribe() throws Exception {

        try (RemoteQueue queue = new RemoteQueue(Config.getInstance())) {

            BehaviorSubject<ModelGameComponent> subject = queue.subscribe(ModelGameComponent.class);
            CompletableFuture<String> future = new CompletableFuture<>();

            subject.subscribe(new Consumer<ModelGameComponent>() {
                @Override
                public void accept(ModelGameComponent luchador) throws Exception {
                    logger.info("Luchador from REDIS subscription=" + luchador.toString());

                    assertEquals(luchador.getId().intValue(), 2);
                    assertEquals(luchador.getCodes().size(), 1);

                    ModelCode code = luchador.getCodes().stream().findFirst().get();
                    assertEquals("start", code.getEvent());
                    assertEquals("move(10)", code.getScript());
                    future.complete("subscribe");
                }
            });

            ModelGameComponent foo = MockLuchador.build(2, "start", "move(10)");
            queue.publish(foo);

            assertEquals("subscribe", future.get(5, TimeUnit.SECONDS));
        }
    }

}