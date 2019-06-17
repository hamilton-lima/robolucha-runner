package com.robolucha.runner;

import org.apache.log4j.Logger;

import com.robolucha.runner.luchador.LuchadorRunner;

import io.swagger.client.model.MainSceneComponent;

public class SceneComponentEventsRunner {
	static Logger logger = Logger.getLogger(SceneComponentEventsRunner.class);

	public static enum EventType {
		ON_START,
		ON_HIT
	}

	private MatchRunner runner;

	public SceneComponentEventsRunner(MatchRunner runner) {
		this.runner = runner;
		for( MainSceneComponent component: runner.getGameDefinition().getSceneComponents()) {
			
		}
	}

	// TODO: prevent multiple executions of ON_HIT
	public void onEvent(EventType type, MainSceneComponent component, LuchadorRunner luchador) {
		logger.info("OnEvent: " + type + " component:" + component.getId() + " luchador:" + luchador.getState());
		
	}

}
