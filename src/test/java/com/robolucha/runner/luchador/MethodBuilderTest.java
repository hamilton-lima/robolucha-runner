package com.robolucha.runner.luchador;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.code.LuchadorScriptDefinition;
import com.robolucha.runner.code.MethodBuilder;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.runner.code.ScriptDefinition;
import com.robolucha.runner.code.ScriptDefinitionFactory;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.swagger.client.model.MainGameComponent;

public class MethodBuilderTest {

    private static Logger logger = Logger.getLogger(MethodBuilderTest.class);

	@Before
	public void setUp() throws Exception {
		
	}
    
    @Test
    public void buildAllWithLuaFunctionInCode() {
        MainGameComponent luchador = MockLuchador.build(1, MethodNames.ON_REPEAT, "print('1');");
        LuchadorScriptDefinition def = ScriptDefinitionFactory.getInstance().getLuchadorScript();
        MethodBuilder.getInstance().buildAll(def, luchador.getCodes());

        long errors = luchador.getCodes().stream().filter(code -> code.getException() != null).map(
                code -> {
                    logger.debug("code exception: >>> " + code.getException());
                    return code;
                }).count();

        assertEquals(0, errors);
    }

    @Test
    public void buildAllWithLuaVariables() {
        MainGameComponent luchador = MockLuchador.build(1, MethodNames.ON_REPEAT, "a = 1;b = 2 \n if a >= b then \n b = a \n end");
        LuchadorScriptDefinition def = ScriptDefinitionFactory.getInstance().getLuchadorScript();
        MethodBuilder.getInstance().buildAll(def, luchador.getCodes());

        long errors = luchador.getCodes().stream().filter(code -> code.getException() != null).map(
                code -> {
                    logger.debug("code exception: >>> " + code.getException());
                    return code;
                }).count();

        assertEquals(0, errors);
    }

    @Test
    public void buildAllCheckMoveMethod() {
        MainGameComponent luchador = MockLuchador.build(1, MethodNames.ON_REPEAT, "move(10)");
        LuchadorScriptDefinition def = ScriptDefinitionFactory.getInstance().getLuchadorScript();
        MethodBuilder.getInstance().buildAll(def, luchador.getCodes());

        long errors = luchador.getCodes().stream().filter(code -> code.getException() != null).map(
                code -> {
                    logger.debug("code exception: >>> " + code.getException());
                    return code;
                }).count();

        assertEquals(0, errors);
    }

    @Test
    public void buildAll() {
        MainGameComponent luchador = MockLuchador.build(1, MethodNames.ON_REPEAT, "fire(1)");
        LuchadorScriptDefinition def = ScriptDefinitionFactory.getInstance().getLuchadorScript();
        MethodBuilder.getInstance().buildAll(def, luchador.getCodes());

        long errors = luchador.getCodes().stream().filter(code -> code.getException() != null).map(
                code -> {
                    logger.debug("code exception: >>> " + code.getException());
                    return code;
                }).count();

        assertEquals(0, errors);
    }

    @Test
    public void buildAllWithError() {
        MainGameComponent luchador = MockLuchador.build(1, MethodNames.ON_REPEAT, "fire(1); nheco");
        LuchadorScriptDefinition def = ScriptDefinitionFactory.getInstance().getLuchadorScript();
        MethodBuilder.getInstance().buildAll(def, luchador.getCodes());

        long errors = luchador.getCodes().stream().filter(code -> code.getException() != null).map(
                code -> {
                    logger.debug("code exception: >>> " + code.getException());
                    return code;
                }).count();

        assertEquals(1, errors);
    }

}