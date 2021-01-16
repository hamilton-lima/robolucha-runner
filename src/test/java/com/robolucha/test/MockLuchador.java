package com.robolucha.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.github.javafaker.Faker;
import com.robolucha.models.LuchadorMatchState;
import com.robolucha.runner.code.MethodDefinition;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.runner.code.lua.LuchadorLuaScriptDefinition;

import io.swagger.client.model.ModelCode;
import io.swagger.client.model.ModelGameComponent;

public class MockLuchador {
	static Logger logger = Logger.getLogger(MockLuchador.class);

	static Faker faker = new Faker();

	public static ModelGameComponent build() {
		ModelGameComponent a = new ModelGameComponent();
		a.setId(faker.hashCode());
		a.setName(faker.name().username());
		a.setCodes(new ArrayList<ModelCode>());
		a.setIsNPC(false);
		return a;
	}

	public static ModelGameComponent build(int id) {
		ModelGameComponent a = build();
		a.setId(id);
		return a;
	}

	public static ModelGameComponent build(int id, String event, String code) {
		ModelGameComponent a = build(id);
		ModelCode c2 = buildCodefromEvent(event, code);
		a.getCodes().add(c2);

		return a;
	}

	public static ModelGameComponent createWithRepeatCode(String repeatCode) {

		ModelGameComponent a = MockLuchador.build();
		ModelCode c = buildCodefromEvent(MethodNames.ON_REPEAT, repeatCode);
		a.getCodes().add(c);
		return a;
	}

	public static ModelCode getRepeatCode(List<ModelCode> codes) {
		return getCode(codes, MethodNames.ON_REPEAT);
	}

	public static ModelCode getCode(List<ModelCode> codes, String event) {
		for (ModelCode code : codes) {
			if (code.getEvent().equals(event)) {
				return code;
			}
		}

		return null;
	}

	public static LuchadorMatchState buildLuchadorMatchState(long id) {
		LuchadorMatchState result = new LuchadorMatchState(false);
		result.setId(id);
		result.setName(faker.name().username());
		return result;
	}

	public static ModelCode buildCode(String event, String script) {
		ModelCode code = new ModelCode();
		code.setGameDefinition(1);
		code.setEvent(event);
		code.setScript(script);
		return code;
	}

	// use the event name to build the start/end of the test script
	public static ModelCode buildCodefromEvent(String event, String code) {
		LuchadorLuaScriptDefinition def = new LuchadorLuaScriptDefinition();
		logger.info("buildcodefromEvent " + event + " " + code);
		
		MethodDefinition foo = def.getDefaultMethods().get(event);
		logger.info("buildcodefromEvent from default methods " + foo);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(foo.getStart());
		buffer.append(code);
		buffer.append(foo.getEnd());

		return buildCode(MethodNames.ALL, buffer.toString());
	}

}
