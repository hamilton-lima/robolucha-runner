package com.robolucha.runner.luchador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.swagger.client.model.ModelCode;
import io.swagger.client.model.ModelGameComponent;

public class LuchadorRunnerTest {

    private static Logger logger = Logger.getLogger(LuchadorRunnerTest.class);

    @Test
    public void testEmptyCode() throws Exception {

        MatchRunner runner = MockMatchRunner.build();

        ModelGameComponent l1 = MockLuchador.build();
        l1.setId(1);
        List<ModelCode> codes = new ArrayList<ModelCode>();

        ModelCode code = new ModelCode();
        code.setEvent("start");
        code.setScript(" "); // EMPTY CODE!!
        l1.getCodes().add(code);

        LuchadorRunner one = new LuchadorRunner(l1, runner);

        // TODO: add observable
        one.run("start");

        // assertTrue(!one.isActive());
        assertTrue("verificar se codigo NAO possui erro apos tentar atualizar codigo vazio",
                code.getException() == null);

        // TODO: update how to get the message
        // MessageVO message = one.getMessage();
        // Assert.assertNull("no message is expected when the code is empty", message);

    }

    @Test
    public void testInvalidCode() throws Exception {

        MatchRunner runner = MockMatchRunner.build();

        ModelGameComponent l1 = MockLuchador.build();
        l1.setId(1);

        ModelCode code = new ModelCode();
        code.setEvent("start");
        code.setScript("this is an invalid code");
        l1.getCodes().add(code);

        LuchadorRunner one = new LuchadorRunner(l1, runner);
        one.run("start");

//        while (one.getCurrentRunner() != null) {
//            // wait for the runner to complete
//            Thread.sleep(50);
//        }

        // assertTrue(!one.isActive());
        assertTrue("verificar se codigo com erro fica com excecao apos tentar atualizar codigo com defeito",
                code.getException() != null);

        // TODO: update how to get the message
        //MessageVO message = one.getMessage();
        //Assert.assertEquals(MessageVO.DANGER, message.type);
    }

    @Test
    public void testNOCODE() throws Exception {

        String[] methods = {"onRepeat", "onHitWall", "onHitOther", "onFound", "onGotDamage", "onListen"};
        MatchRunner runner = MockMatchRunner.build();

        for (int i = 0; i < methods.length; i++) {
            ModelGameComponent l1 = MockLuchador.build();
            l1.setId(1);

            LuchadorRunner one = new LuchadorRunner(l1, runner);
            one.run(methods[i]);
        }

        ModelGameComponent l1 = MockLuchador.build();
        l1.setId(1);
        LuchadorRunner one = new LuchadorRunner(l1, runner);
        one.run("invalidMethod");

//        while (one.getCurrentRunner() != null) {
//            // wait for the runner to complete
//            Thread.sleep(50);
//        }

        // TODO: update how to get the message
        // MessageVO message = one.getMessage();
        //Assert.assertNull("no error when call an not existing or empty function", message);
    }

    /**
     * verifica a existencia dos metodos padrao existentes na classe
     * JavascriptFacade na implementacao javascript do LutchadorRunner
     *
     * @throws ScriptException
     * @throws NoSuchMethodException
     */
    @Test
    public void testFacadeMethodsCall() throws Exception, ScriptException {
        ModelGameComponent l1 = MockLuchador.build();
        l1.setId(1);

        ModelCode c2 = new ModelCode();
        c2.setEvent("start");
        c2.setScript("");
        l1.getCodes().add(c2);

        LuchadorRunner one = new LuchadorRunner(l1, MockMatchRunner.build());

//        c2.setScript("move(10);");
//        one.updateCodeEngine();
//        one.run("start");
//        assertEquals("move(10)", one.facade.getLastCall());
//
//        c2.setScript("stop();");
//        one.updateCodeEngine();
//        one.run("start");
//        assertEquals("stop()", one.facade.getLastCall());
//
//        c2.setScript("reset();");
//        one.updateCodeEngine();
//        one.run("start");
//        assertEquals("reset()", one.facade.getLastCall());
//
//        c2.setScript("turn(11);");
//        one.updateCodeEngine();
//        one.run("start");
//        assertEquals("turn(11)", one.facade.getLastCall());
//
//        c2.setScript("turnGun(45);");
//        one.updateCodeEngine();
//        one.run("start");
//        assertEquals("turnGun(45)", one.facade.getLastCall());
//
//        c2.setScript("fire(3);");
//        one.updateCodeEngine();
//        one.run("start");
//        assertEquals("fire(3)", one.facade.getLastCall());
//
//        c2.setScript("punch();");
//        one.updateCodeEngine();
//        one.run("start");
//        assertEquals("punch()", one.facade.getLastCall());

    }

    /**
     * verifica a existencia da variavel "me" do tipo LutchadorMatchState no
     * contexto do codigo de start
     *
     * @throws ScriptException
     * @throws NoSuchMethodException
     */
    @Test
    public void testVariableME() throws Exception, ScriptException {

        ModelGameComponent l1 = MockLuchador.build();
        l1.setId(1);
        List<ModelCode> codes = new ArrayList<ModelCode>();

        ModelCode code = new ModelCode();
        code.setEvent("start");
        code.setScript("function getLife(){debug(me); return me.life;}");
        l1.getCodes().add(code);

        MatchRunner runner = MockMatchRunner.build();
        LuchadorRunner one = new LuchadorRunner(l1, runner);

        String result = one.getString("getLife()");
        logger.debug(">>>> #1 call result = " + result);
        String expected = Integer.toString(runner.getGameDefinition().getLife());
        assertTrue(expected.equals(result));

        one.damage(3);
        one.run(MethodNames.ON_REPEAT);

        while (one.currentRunner != null) {
            // wait for the runner to complete
            Thread.sleep(50);
        }

        result = one.getString("getLife()");
        logger.debug(">>>> #2 call result = " + result);
        assertEquals("17", result);
    }

    @Test
    public void testMultipleLutchadores() throws Exception, ScriptException {

        MatchRunner runner = MockMatchRunner.build();
        ModelGameComponent l1 = MockLuchador.build();
        l1.setId(1);

        ModelCode code = new ModelCode();
        code.setEvent("start");
        code.setScript("var counter = 3; function count(a){counter += a; return counter;}");
        l1.getCodes().add(code);

        LuchadorRunner one = new LuchadorRunner(l1, runner);

        ModelGameComponent l2 = MockLuchador.build(2,
                "start",
                "var counter = 10; function count(a){counter += a; debug('updated counter=' + counter); return counter;}");

        LuchadorRunner two = new LuchadorRunner(l2, runner);
        two.run("count", 42);

        while (one.currentRunner != null) {
            // wait for the runner to complete
            Thread.sleep(50);
        }

        String resultOne = (String) one.getString("counter");
        String resultTwo = (String) two.getString("counter");

        logger.debug(">>>> call resultOne = " + resultOne);
        logger.debug(">>>> call resultTwo = " + resultTwo);

        assertEquals("3", resultOne);
        assertEquals("52", resultTwo);
    }

    @Test
    public void testStart() throws Exception, ScriptException {
        ModelGameComponent l1 = MockLuchador.build(1,
                "start",
                "local counter = 3\n"
                +"function count(a)\n"
                + "  counter = counter + a\n"
                + "  return counter\n "
                + "end");

        MatchRunner runner = MockMatchRunner.build();
        LuchadorRunner one = new LuchadorRunner(l1, runner);
        one.run("count", 42);

        MockMatchRunner.start(runner);

        Double result = Double.parseDouble(one.getString("return counter"));
        logger.debug("count call result = " + result);
        Double expected = 45.0;
        assertTrue(expected.equals(result));
    }


    @Test
    public void testMethodsWithoutParameter() throws Exception, ScriptException {

        String[] methods = {MethodNames.ON_REPEAT, MethodNames.ON_HIT_WALL};
        MatchRunner runner = MockMatchRunner.build();

        for (int i = 0; i < methods.length; i++) {
            ModelGameComponent l1 = MockLuchador.build();
            l1.setId(1);
            List<ModelCode> codes = new ArrayList<ModelCode>();

            ModelCode code = new ModelCode();
            code.setEvent(methods[i]);
            code.setScript("counter ++; return counter;");
            codes.add(code);

            ModelCode c2 = new ModelCode();
            c2.setEvent("start");
            c2.setScript("var counter = 3;");
            codes.add(c2);

            l1.getCodes().addAll(codes);

            LuchadorRunner one = new LuchadorRunner(l1, runner);
            one.run(methods[i]);

            String result = one.getString("return counter");
            MockMatchRunner.start(runner);

            logger.debug("===== method=" + methods[i] + ", count call result = " + result);
            Double expected = 4.0;
            assertTrue(expected.equals(result));

        }

    }

}
