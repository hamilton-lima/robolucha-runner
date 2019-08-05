package com.robolucha.game;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.swagger.client.model.ModelCode;
import io.swagger.client.model.ModelGameComponent;

public class CheckMoveGrudadoNaParedeTest {

    private static Logger logger = Logger
            .getLogger(CheckMoveGrudadoNaParedeTest.class);

    @Before
    public void setUp() throws Exception {

        logger.setLevel(Level.ALL);
    }

    @Test
    public void testRun() throws Exception {

        MatchRunner match = MockMatchRunner.build();
        match.getGameDefinition().setMinParticipants(1);

        ModelGameComponent a = MockLuchador.build(1, MethodNames.ON_REPEAT, "move(-100);");

        ModelCode c1 = MockLuchador.buildCode(MethodNames.ON_HIT_WALL, "turn(45);");
        a.getCodes().add(c1);

        match.add(a);

        MockMatchRunner.start(match);

        LuchadorRunner runnerA = match.getRunners().get(1L);

        // quase grudado no limite superior do mapa
        runnerA.getState().setX((runnerA.getSize() / 2) + 2);
        runnerA.getState().setY((runnerA.getSize() / 2) + 2);

        logger.debug("--- A : " + runnerA.getState().getPublicState());
        double startAngle = runnerA.getState().getPublicState().angle;

        // stop the match
        Thread.sleep(2500);
        match.kill();
        Thread.sleep(1500);

        logger.debug("--- A depois : " + runnerA.getState().getPublicState());

        double finalAngle = runnerA.getState().getPublicState().angle;

        logger.debug(String.format("*** resultados : a[%s, %s]", startAngle,
                finalAngle));

        assertTrue("verifica se lutchador girou ao colidir com parede",
                finalAngle > startAngle);


    }
}
