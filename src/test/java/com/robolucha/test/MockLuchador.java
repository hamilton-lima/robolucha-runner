package com.robolucha.test;

import java.util.List;

import com.github.javafaker.Faker;
import com.robolucha.models.LuchadorMatchState;
import com.robolucha.runner.luchador.MethodNames;

import io.swagger.client.model.MainCode;
import io.swagger.client.model.MainGameComponent;

public class MockLuchador {

	static Faker faker = new Faker();

	public static MainGameComponent build() {
		MainGameComponent a = new MainGameComponent();
		a.setName(faker.name().username()); // TODO: should have 30 chars??
		return a;
	}

	public static MainGameComponent build(int id) {
		MainGameComponent a = build();
		a.setId(id);
		return a;
	}

	public static MainGameComponent build(int id, String event, String code) {
		MainGameComponent a = build(id);

		MainCode c2 = new MainCode();
		c2.setEvent(event);
		c2.setScript(code);
		a.getCodes().add(c2);

		return a;
	}

	public static MainGameComponent createWithRepeatCode(String repeatCode) {

		MainGameComponent a = MockLuchador.build();
		a.setName(faker.name().username());

		MainCode c = new MainCode();
		c.setEvent(MethodNames.ON_REPEAT);
		c.setScript(repeatCode);

		a.getCodes().add(c);
		return a;
	}

	public static MainCode getRepeatCode(List<MainCode> codes) {
		return getCode(codes, MethodNames.ON_REPEAT);
	}

	public static MainCode getCode(List<MainCode> codes, String event) {
		for (MainCode code : codes) {
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

	public static MainCode buildCode(String event, String script) {
		MainCode code = new MainCode();
		code.setEvent(event);
		code.setScript(script);
		return code;
	}
}
