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

import io.swagger.client.model.ModelCode;
import io.swagger.client.model.ModelSceneComponent;

public class SceneComponentEventsRunner {
	static Logger logger = Logger.getLogger(SceneComponentEventsRunner.class);

	private class SceneComponentRunner {
		ModelSceneComponent component;
		MatchScriptDefinition scriptDefinition;
		ModelCode code;

		public SceneComponentRunner(ModelSceneComponent component, MatchScriptDefinition scriptDefinition, ModelCode code) {
			this.component = component;
			this.scriptDefinition = scriptDefinition;
			this.code = code ;
		}
	}

	private Map<Integer, SceneComponentRunner> runners;
	MatchFacade facade;

	public SceneComponentEventsRunner(MatchRunner runner) throws Exception {
		runners = new HashMap<Integer, SceneComponentEventsRunner.SceneComponentRunner>();
		facade = new MatchFacade(runner);

		for (ModelSceneComponent component : runner.getGameDefinition().getSceneComponents()) {
			MatchScriptDefinition scriptDefinition = ScriptDefinitionFactory.getInstance().getMatchScript();
			scriptDefinition.loadDefaultLibraries();

			ModelCode code = MethodBuilder.getInstance().filter(component.getCodes());
			MethodBuilder.getInstance().build(scriptDefinition, code);
			
			runners.put(component.getId(), new SceneComponentRunner(component, scriptDefinition, code));
		}
	}

	boolean hasException(ModelCode code) {
		return code != null && code.getException() != null && code.getException().trim().length() > 0;
	}

	public void onHit(ModelSceneComponent component, LuchadorRunner luchador) {
		logger.debug("OnHit: component:" + component.getId() + " luchador:" + luchador.getState());

		SceneComponentRunner runner = runners.get(component.getId());

		if (runner.code != null) {
			if (hasException(runner.code)) {
				logger.warn("onHit triggered, but code has Exception and won't be executed: "
						+ JSONFormat.clean(runner.code.toString()));
			} else {
				Object[] parameter = new Object[] { luchador.getState().getPublicState() };

				if( logger.isDebugEnabled() ) {
					logger.debug("onHit Calling the scriptDefinition run() : " + JSONFormat.clean(component.toString())
					+ " parameter: " + Arrays.toString(parameter));
				}

				runner.scriptDefinition.run(facade, MethodNames.ON_HIT_OTHER, parameter);
			}
		}
	}

}
