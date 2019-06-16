package com.robolucha.runner;

import org.apache.log4j.Logger;

import com.robolucha.runner.luchador.LuchadorRunner;

import io.swagger.client.model.MainSceneComponent;

public class SceneComponentEventsRunner {
	static Logger logger = Logger.getLogger(SceneComponentEventsRunner.class);

	public static enum EventType {
		ON_HIT
	}

	// TODO: prevent multiple executions of ON_HIT
	public void onEvent(EventType type, MainSceneComponent component, LuchadorRunner luchador) {
		logger.info("OnEvent: " + type + " component:" + component + " luchador:" + luchador.getState());
	}

}
