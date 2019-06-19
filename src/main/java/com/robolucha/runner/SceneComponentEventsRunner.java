package com.robolucha.runner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.robolucha.runner.code.MatchScriptDefinition;
import com.robolucha.runner.code.MethodBuilder;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.runner.code.ScriptDefinitionFactory;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.shared.JSONFormat;

import io.swagger.client.model.MainCode;
import io.swagger.client.model.MainSceneComponent;

public class SceneComponentEventsRunner {
	static Logger logger = Logger.getLogger(SceneComponentEventsRunner.class);

	private class SceneComponentRunner {
		MainSceneComponent component;
		MatchScriptDefinition scriptDefinition;

		public SceneComponentRunner(MainSceneComponent component, MatchScriptDefinition scriptDefinition) {
			this.component = component;
			this.scriptDefinition = scriptDefinition;
		}
	}

	private Map<Integer, SceneComponentRunner> runners;
	MatchFacade facade;

	public SceneComponentEventsRunner(MatchRunner runner) throws Exception {
		runners = new HashMap<Integer, SceneComponentEventsRunner.SceneComponentRunner>();
		facade = new MatchFacade(runner);

		for (MainSceneComponent component : runner.getGameDefinition().getSceneComponents()) {
			MatchScriptDefinition scriptDefinition = ScriptDefinitionFactory.getInstance().getMatchScript();
			scriptDefinition.loadDefaultLibraries();
			
			logger.info("Builds gamedefinition codes: " + JSONFormat.clean(component.getCodes().toString()));
			MethodBuilder.getInstance().buildAll(scriptDefinition, component.getCodes());

			// output code errors
			for (MainCode code : component.getCodes()) {
				if (hasException(code)) {
					logger.warn("Exception in the code: " + JSONFormat.clean(code.toString()));
				}
			}

			runners.put(component.getId(), new SceneComponentRunner(component, scriptDefinition));
		}
	}

	boolean hasException(MainCode code) {
		return code.getException() != null && code.getException().trim().length() > 0;
	}

	public void onHit(MainSceneComponent component, LuchadorRunner luchador) {
		logger.info("OnHit: component:" + component.getId() + " luchador:" + luchador.getState());

		SceneComponentRunner runner = runners.get(component.getId());
		MainCode code = runner.component.getCodes().stream()
				.filter((c) -> c.getEvent().equals(MethodNames.ON_HIT_OTHER)).findAny().orElse(null);

		if (hasException(code)) {
			logger.warn("onHit triggered, but code has Exception and won't be executed: "
					+ JSONFormat.clean(code.toString()));
		} else {
			Object[] parameter = new Object[] { luchador.getState().getPublicState() };

			logger.info("onHit Calling the scriptDefinition run() : " + JSONFormat.clean(component.toString())
					+ " parameter: " + Arrays.toString(parameter));

			runner.scriptDefinition.run(facade, MethodNames.ON_HIT_OTHER, parameter);
		}

	}

}
