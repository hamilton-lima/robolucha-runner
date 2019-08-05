package com.robolucha.game;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.models.LuchadorPublicState;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.swagger.client.model.ModelGameComponent;

public class CheckTurnJuntoComTurnGunTest {

    private static Logger logger = Logger
            .getLogger(CheckTurnJuntoComTurnGunTest.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testRun() throws Exception {

        MatchRunner match = MockMatchRunner.build();
        match.getGameDefinition().setMinParticipants(1);
        ModelGameComponent a = MockLuchador.build(1, MethodNames.ON_REPEAT, "move(10);turn(45);turnGun(-45);");

        match.add(a);

        MockMatchRunner.start(match);
        LuchadorRunner runnerA = match.getRunners().get(1L);

        // quase grudado no limite superior do mapa
        runnerA.getState().setX((runnerA.getSize() / 2) + 2);
        runnerA.getState().setY((runnerA.getSize() / 2) + 2);
        runnerA.getState().setGunAngle(300);

        logger.debug("--- A : " + runnerA.getState().getPublicState());
        LuchadorPublicState start = (LuchadorPublicState) runnerA.getState().getPublicState();

        // stop the match
        Thread.sleep(1500);
        match.kill();
        Thread.sleep(1500);

        logger.debug("--- A depois : " + runnerA.getState().getPublicState());
        LuchadorPublicState end = (LuchadorPublicState) runnerA.getState().getPublicState();

        logger.debug(String.format("*** resultados angle : a[%s, %s]",
                start.angle, end.angle));

        logger.debug(String.format("*** resultados gun angle : a[%s, %s]",
                start.gunAngle, end.gunAngle));

        assertTrue("verifica se lutchador girou ", end.angle > start.angle);

        assertTrue("verifica se lutchador girou arma ",
                end.gunAngle < start.gunAngle);

    }
}
