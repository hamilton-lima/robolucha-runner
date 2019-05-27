package com.robolucha.game;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.game.event.LuchadorEvent;
import com.robolucha.game.event.LuchadorEventListener;
import com.robolucha.game.event.OnFoundEvent;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.swagger.client.model.MainCode;
import io.swagger.client.model.MainGameComponent;

public class CheckRadarActionTest {

    private static Logger logger = Logger.getLogger(CheckRadarActionTest.class);
    public static String state = "";

    @Before
    public void setUp() throws Exception {
        CheckRadarActionTest.state = "empty";
    }

    @Test
    public void testRun() throws Exception {

        MatchRunner match = MockMatchRunner.build();

        MainGameComponent a = MockLuchador.build();
        a.setId(1);

        MainGameComponent b = MockLuchador.build();
        b.setId(2);

        MainCode c = MockLuchador.buildCode("onFound","// does nothing ... ");
        a.getCodes().add(c);

        match.add(a);
        match.add(b);

        // adiciona listener para receber evento de onfound ...
        match.addListener(new LuchadorEventListener() {

            public void listen(LuchadorEvent event) {
                logger.debug(">>> event : " + event);

                if (event instanceof OnFoundEvent) {
                    CheckRadarActionTest.state = event.getKey();
                }
            }
        });

        MockMatchRunner.start(match);

        LuchadorRunner runnerA = match.getRunners().get(1L);
        LuchadorRunner runnerB = match.getRunners().get(2L);

        runnerA.getState().setX(100);
        runnerA.getState().setY(100);
        runnerA.getState().setAngle(90);
        runnerA.getState().setGunAngle(90);

        runnerB.getState().setX(100);
        runnerB.getState().setY(250);

        logger.debug("--- A : " + runnerA.getState().getPublicState());
        logger.debug("--- B : " + runnerB.getState().getPublicState());

        // stop the match
        Thread.sleep(500);
        match.kill();
        Thread.sleep(500);

        logger.debug("--- A depois : " + runnerA.getState().getPublicState());
        logger.debug("--- B depois : " + runnerB.getState().getPublicState());

        assertEquals("verifica se evento onfound foi disparado em A", "onFound.2", CheckRadarActionTest.state);
    }

}
