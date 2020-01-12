package com.robolucha.test;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;
import com.robolucha.models.LuchadorMatchState;
import com.robolucha.runner.code.MethodNames;

import io.swagger.client.model.ModelCode;
import io.swagger.client.model.ModelGameComponent;

public class MockLuchador {

	static Faker faker = new Faker();

	public static ModelGameComponent build() {
		ModelGameComponent a = new ModelGameComponent();
		a.setId(faker.hashCode());
		a.setName(faker.name().username()); 
		a.setCodes(new ArrayList<ModelCode>());
		return a;
	}

	public static ModelGameComponent build(int id) {
		ModelGameComponent a = build();
		a.setId(id);
		return a;
	}

	public static ModelGameComponent build(int id, String event, String code) {
		ModelGameComponent a = build(id);

		ModelCode c2 = new ModelCode();
		c2.setEvent(event);
		c2.setScript(code);
		c2.setGameDefinition(1);
		a.getCodes().add(c2);

		return a;
	}

	public static ModelGameComponent createWithRepeatCode(String repeatCode) {

		ModelGameComponent a = MockLuchador.build();

		ModelCode c = new ModelCode();
		c.setEvent(MethodNames.ON_REPEAT);
		c.setScript(repeatCode);

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
		LuchadorMatchState result = new LuchadorMatchState();
		result.setId(id);
		result.setName(faker.name().username());
		return result;
	}

	public static ModelCode buildCode(String event, String script) {
		ModelCode code = new ModelCode();
		code.setEvent(event);
		code.setScript(script);
		return code;
	}
}
