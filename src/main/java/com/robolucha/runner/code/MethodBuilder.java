package com.robolucha.runner.code;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.robolucha.runner.luchador.LuchadorRunner;

import io.swagger.client.model.MainCode;

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

	public void buildAll(LuchadorRunner runner, List<MainCode> codes) {

		HashMap<String, MainCode> local = populateLocalHash(codes);
		String script = "";
		Integer codeId = 0;
		String key = null;
		
		HashMap<String, MethodDefinition> methods = ScriptDefinitionFactory.getInstance().getDefault()
				.getDefaultMethods();

		Iterator<String> iterator = methods.keySet().iterator();
		while (iterator.hasNext()) {
			StringBuffer buffer = new StringBuffer();

			key = (String) iterator.next();
			script = "";
			codeId = 0;

			MainCode code = null;

			if (local.containsKey(key)) {
				code = local.get(key);
				script = code.getScript();
				codeId = code.getId();
			}

			logger.debug("defining code: key=" + key + ", script=" + script);
			MethodDefinition definition = methods.get(key);
            logger.debug("Using method definition: " + definition);

            buffer.append(definition.getStart());
			buffer.append(script);
			buffer.append(definition.getEnd());

			String createdSourceMainCode = buffer.toString();
			if (logger.isDebugEnabled()) {
				logger.debug(createdSourceMainCode);
			}

			try {
				runner.eval(key, createdSourceMainCode);
			} catch (Exception e) {
				String message = "Error compiling code code.id=" + codeId + " gamecomponent.id="
						+ runner.getGameComponent().getId();

				if (logger.isDebugEnabled()) {
					logger.debug(">>>> code=" + code);
					logger.debug(">>>> error=" + e.getMessage());
				}

				if (code != null) {
					runner.saveExceptionToCode(code.getEvent(), e.getMessage());
				}

				if (logger.isDebugEnabled()) {
					logger.error(message, e);
				} else {
					logger.error(message);
				}

				if (key != null) {
					local.get(key).setException(e.getMessage());
				}
			}
		}

	}

	private HashMap<String, MainCode> populateLocalHash(List<MainCode> codes) {
		HashMap<String, MainCode> result = new HashMap<String, MainCode>();
		if (codes != null) {
			Iterator<MainCode> iterator = codes.iterator();
			while (iterator.hasNext()) {
				MainCode code = iterator.next();
				result.put(code.getEvent(), code);
			}
		}
		return result;
	}

	public void build(LuchadorRunner runner, MainCode code) {

		logger.debug("building code: " + code);
		if (code == null) {
			return;
		}

		String script = code.getScript();
		Integer codeId = code.getId();
		String key = code.getEvent();
		
		ScriptDefinition scriptDef = ScriptDefinitionFactory.getInstance().getDefault();
		HashMap<String, MethodDefinition> methods = scriptDef.getDefaultMethods();

		MethodDefinition definition = methods.get(key);
		logger.debug("Using method definition: " + definition);

		StringBuffer buffer = new StringBuffer();
		buffer.append(definition.getStart());
		buffer.append(script);
		buffer.append(definition.getEnd());

		String createdSourceMainCode = buffer.toString();
		logger.debug(createdSourceMainCode);

		try {
			runner.eval(key, createdSourceMainCode);
		} catch (Exception e) {
			logger.error("error compiling code: " + codeId);
			if (key != null) {
				code.setException(e.getMessage());
			}
		} finally {
			scriptDef.afterCompile();
		}

	}

}
