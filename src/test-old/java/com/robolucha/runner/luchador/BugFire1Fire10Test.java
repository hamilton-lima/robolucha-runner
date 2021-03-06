package com.robolucha.runner.luchador;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.swagger.client.model.ModelGameComponent;

/**
 * @author hamiltonlima
 * @raquel - quando usando fire(1) no repeat nao consegue disparar um fire(10)
 * usando onfound
 */
public class BugFire1Fire10Test {

    private static Logger logger = Logger.getLogger(BugFire1Fire10Test.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testRun() throws Exception {

        MatchRunner match = MockMatchRunner.build();
        match.getGameDefinition().setMinParticipants(1);

        ModelGameComponent a = MockLuchador.build(1, MethodNames.ON_REPEAT, "fire(1);");
        a.getCodes().add(MockLuchador.buildCode(MethodNames.ON_START, "var foundIt = 0;"));
        a.getCodes().add(MockLuchador.buildCode(MethodNames.ON_FOUND, "foundIt = 1; fire(10);"));

        match.add(a);

        match.add(MockLuchador.build(2, MethodNames.ON_REPEAT, "turn(20);"));
        MockMatchRunner.start(match);

        LuchadorRunner runnerA = match.getRunners().get(1L);
        LuchadorRunner runnerB = match.getRunners().get(2L);

        runnerA.getState().setX(100);
        runnerA.getState().setY(100);

        runnerB.getState().setX(200);
        runnerB.getState().setY(100);

        logger.debug("--- A : " + runnerA.getState().getPublicState());
        logger.debug("--- B : " + runnerB.getState().getPublicState());

        // stop the match
        Thread.sleep(3500);
        match.kill();
        Thread.sleep(500);

        logger.debug("--- A depois : " + runnerA.getState().getPublicState());
        logger.debug("--- B depois : " + runnerB.getState().getPublicState());

        String foundIt = runnerA.getString("foundIt");
        logger.debug("*** foundIt = " + foundIt);

        assertTrue("found foi executado ? ", foundIt.equals("1.0"));
        assertTrue("B recebeu o disparo de 10 ?", runnerB.getState()
                .getPublicState().life < 10);

    }
}
