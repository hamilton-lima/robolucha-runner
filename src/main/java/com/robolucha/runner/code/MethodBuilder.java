package com.robolucha.runner.code;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.robolucha.shared.JSONFormat;

import io.swagger.client.model.ModelCode;
import io.swagger.client.model.ModelGameDefinition;

/**
 * 
 * @author hamiltonlima
 *
 */
public class MethodBuilder {

	private static MethodBuilder instance = new MethodBuilder();
	private static Logger logger = Logger.getLogger(MethodBuilder.class);

	public static MethodBuilder getInstance() {
		return instance;
	}

	public ModelCode empty(Integer id) {
		ModelCode result = new ModelCode();
		result.setEvent(MethodNames.ALL);
		result.setScript("");
		result.setGameDefinition(id);
		return result;
	}

	public void build(ScriptDefinition scriptDefinition, ModelCode code) {
		try {
			if (logger.isDebugEnabled()) {
				logger.debug(code);
			}

			scriptDefinition.eval(code.getScript());
		} catch (Exception e) {
			logger.warn("Error compiling code: " + code.getId());
			code.setException(e.getMessage());
		} finally {
			scriptDefinition.afterCompile();
		}
	}

	public ModelCode filter(List<ModelCode> codes, ModelGameDefinition gameDefinition) {
		ModelCode result;

		if (codes == null) {
			result = empty(gameDefinition.getId());
			logger.warn("No LIST of codes, will return empty object");
		} else {
			logger.info(String.format("Look for gamedefinition code: gamedefinition: %s", gameDefinition.getId()));

			// list all the codes when updating
			for (ModelCode code : codes) {
				logger.info(String.format("Code gamedefinition: %s, event: %s, script: %s", code.getGameDefinition(),
						code.getEvent(), code.getScript()));
			}

			// Identify the code for the current gamedefinition with event == "ALL"
			List<ModelCode> codes4CurrentGameDefinition = codes.stream()
					.filter(line -> gameDefinition.getId().equals(line.getGameDefinition())
							&& line.getEvent().equals(MethodNames.ALL))
					.collect(Collectors.toList());

			if (codes4CurrentGameDefinition.isEmpty()) {
				result = empty(gameDefinition.getId());
				logger.warn("No code found, will return empty object");
			} else {
				result = codes4CurrentGameDefinition.get(0);
				logger.info("Found THE code: " + JSONFormat.clean(result.toString()));
			}
		}

		return result;
	}
	
	// filter event=all only
	public ModelCode filter(List<ModelCode> codes) {
		ModelCode result;

		if (codes == null) {
			result = empty(0);
			logger.warn("No LIST of codes, will return empty object");
		} else {
			logger.info("Looking for code with event = all");

			// list all the codes when updating
			for (ModelCode code : codes) {
				logger.info(String.format("Code event: %s, script: %s", code.getEvent(), code.getScript()));
			}

			// Identify the code for the current gamedefinition with event == "ALL"
			List<ModelCode> codes4CurrentGameDefinition = codes.stream()
					.filter(line -> line.getEvent().equals(MethodNames.ALL))
					.collect(Collectors.toList());

			if (codes4CurrentGameDefinition.isEmpty()) {
				result = empty(0);
				logger.warn("No code found, will return empty object");
			} else {
				result = codes4CurrentGameDefinition.get(0);
				logger.info(String.format("Found THE code, id: %s, script: %s", result.getId(), result.getScript()));
			}
		}

		return result;
	}

}
