package com.robolucha.game;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.swagger.client.model.MainCode;
import io.swagger.client.model.MainGameComponent;

public class CheckRespawnTest {

    private static Logger logger = Logger.getLogger(CheckRadarActionTest.class);

    @Test
    public void testRun() throws Exception {

        MatchRunner match = MockMatchRunner.build();
        match.getGameDefinition().setMinParticipants(1);

        MainGameComponent a = MockLuchador.build();
        a.setId(1);

        MainGameComponent b = MockLuchador.build();
        b.setId(2);

        MainCode c = MockLuchador.buildCode(MethodNames.ON_REPEAT, "fire(2)");
        a.getCodes().add(c);

        match.add(a);
        match.add(b);

        MockMatchRunner.start(match);
        LuchadorRunner runnerA = match.getRunners().get(1L);
        LuchadorRunner runnerB = match.getRunners().get(2L);

        runnerA.getState().setX(100);
        runnerA.getState().setY(100);
        runnerA.getState().setAngle(90);
        runnerA.getState().setGunAngle(90);

        runnerB.getState().setX(100);
        runnerB.getState().setY(150);
        runnerB.getState().setGunAngle(-90);
        runnerB.getState().setLife(1);

        logger.debug("--- A : " + runnerA.getState().getPublicState());
        logger.debug("--- B : " + runnerB.getState().getPublicState());

        // stop the match
        Thread.sleep((long) match.getGameDefinition().getRespawnCooldown());

        match.kill();
        Thread.sleep(500);

        logger.debug("--- A depois : " + runnerA.getState().getPublicState());
        logger.debug("--- B depois : " + runnerB.getState().getPublicState());

        assertTrue("verifica se lutchador esta ativo apos ter sido eliminado",
                runnerA.isActive());
        assertTrue("verifica se lutchador esta ativo apos ter sido eliminado",
                runnerB.isActive());

    }
}
