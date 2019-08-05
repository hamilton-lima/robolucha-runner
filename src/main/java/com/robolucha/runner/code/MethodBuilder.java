package com.robolucha.runner.code;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import io.swagger.client.model.ModelCode;

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

	public void buildAll(ScriptDefinition scriptDef, List<ModelCode> codes) {

		HashMap<String, ModelCode> local = populateLocalHash(codes);
		String script = "";
		Integer codeId = 0;
		String key = null;

		HashMap<String, MethodDefinition> methods = scriptDef.getDefaultMethods();

		Iterator<String> iterator = methods.keySet().iterator();
		while (iterator.hasNext()) {
			StringBuffer buffer = new StringBuffer();

			key = (String) iterator.next();
			script = "";
			codeId = 0;

			ModelCode code = null;

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
				scriptDef.eval(createdSourceMainCode);
			} catch (Exception e) {
				logger.warn("Error compiling code: " + codeId);

				if (key != null) {
					local.get(key).setException(e.getMessage());
				}
			}
		}

	}

	private HashMap<String, ModelCode> populateLocalHash(List<ModelCode> codes) {
		HashMap<String, ModelCode> result = new HashMap<String, ModelCode>();
		if (codes != null) {
			Iterator<ModelCode> iterator = codes.iterator();
			while (iterator.hasNext()) {
				ModelCode code = iterator.next();
				result.put(code.getEvent(), code);
			}
		}
		return result;
	}

	public void build(ScriptDefinition scriptDef, ModelCode code) {

		logger.debug("building code: " + code);
		if (code == null) {
			return;
		}

		String script = code.getScript();
		Integer codeId = code.getId();
		String key = code.getEvent();

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
			scriptDef.eval(createdSourceMainCode);
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
