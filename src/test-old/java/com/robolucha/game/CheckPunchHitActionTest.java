package com.robolucha.game;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.game.event.LuchadorEvent;
import com.robolucha.game.event.LuchadorEventListener;
import com.robolucha.game.event.OnGotDamageEvent;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.swagger.client.model.ModelGameComponent;

public class CheckPunchHitActionTest {

    private static Logger logger = Logger.getLogger(CheckRadarActionTest.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testRun() throws Exception {

        MatchRunner match = MockMatchRunner.build();

        ModelGameComponent a = MockLuchador.build(1, MethodNames.ON_REPEAT, "punch();");
        ModelGameComponent b = MockLuchador.build(2, MethodNames.ON_REPEAT, "punch();");

        match.add(a);
        match.add(b);

        // adiciona listener para receber evento de onfound ...
        match.addListener(new LuchadorEventListener() {

            public void listen(LuchadorEvent event) {
                logger.debug(">>> event : " + event);

                if (event instanceof OnGotDamageEvent) {
                    logger.debug("GOT DAMAGE !! : " + event);
                }
            }
        });

        MockMatchRunner.start(match);

        LuchadorRunner runnerA = match.getRunners().get(1L);
        LuchadorRunner runnerB = match.getRunners().get(2L);

        runnerA.getState().setX(100);
        runnerA.getState().setY(100);
        runnerA.getState().setAngle(90);

        runnerB.getState().setX(100);

        // coloca logo abaixo a distancia de meio corpo
        // suficicente para dar angulo para o soco !!
        int distance = (int) (runnerA.getSize() * 1.5);
        distance--;

        runnerB.getState().setY(100 + distance);
        runnerB.getState().setAngle(-90);

        logger.debug("--- A : " + runnerA.getState().getPublicState());
        logger.debug("--- B : " + runnerB.getState().getPublicState());

        // stop the match
        Thread.sleep(500);
        match.kill();
        Thread.sleep(500);

        logger.debug("--- A depois : " + runnerA.getState().getPublicState());
        logger.debug("--- B depois : " + runnerB.getState().getPublicState());

        assertEquals("verifica se lutchador abaixo do outro recebeu o soco",
                18.0, runnerB.getState().getLife(), 0.001);

        assertEquals("verifica se lutchador acima do outro recebeu o soco", 18.,
                runnerA.getState().getLife(), 0.001);

    }
}
