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
 * atraves da variavel me com possibilidade de posicionar o lutchador
 * arbitrariamente.
 *
 * @author hamiltonlima
 */
public class BugAcessoVariavelMeTest {

    private static Logger logger = Logger.getLogger(BugAcessoVariavelMeTest.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testRun() throws Exception {

        MatchRunner match = MockMatchRunner.build();
        match.getGameDefinition().setMinParticipants(1);

        ModelGameComponent a = MockLuchador.build(1, MethodNames.ON_REPEAT, "me.x = 200;");
        LuchadorRunner runnerA = match.add(a).blockingFirst();

        MockMatchRunner.start(match);

        runnerA.getState().setX(100);
        runnerA.getState().setY(100);

        logger.debug("--- A : " + runnerA.getState().getPublicState());
        logger.debug("--- A : " + runnerA.getState());

        // stop the match
        Thread.sleep(500);
        match.kill();
        Thread.sleep(500);

        logger.debug("--- A depois : " + runnerA.getState().getPublicState());
        logger.debug("--- A depois : " + runnerA.getState());

        assertTrue("verifica se lutchador ficou no lugar certo ...", runnerA.getState().getX() == 100);

    }
}
