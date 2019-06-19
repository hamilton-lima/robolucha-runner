package com.robolucha.runner.code.lua;

import java.util.Arrays;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import com.robolucha.runner.MatchFacade;
import com.robolucha.runner.SceneComponentEventsRunner;
import com.robolucha.runner.code.MethodDefinition;
import com.robolucha.runner.code.ScriptDefinition;
import com.robolucha.runner.code.ScriptFacade;
import com.robolucha.runner.luchador.LuchadorFacade;

public abstract class LuaScriptDefinition implements ScriptDefinition {

	static Logger logger = Logger.getLogger(LuaScriptDefinition.class);

	protected HashMap<String, MethodDefinition> methods;
	protected LuaVM lua;

	public HashMap<String, MethodDefinition> getDefaultMethods() {
		return methods;
	}

	public LuaScriptDefinition() {
		methods = new HashMap<String, MethodDefinition>();
		lua = new LuaVM();
	}

	public void afterCompile() {

	}

	public void run(ScriptFacade facade, String name, Object... parameter) {

		try {
			MatchFacade localFacade = (MatchFacade) facade;
			lua.put("__internal", localFacade);
			logger.debug("__internal assigned with matchfacade");

		} catch (Exception e) {
			logger.error("cant convert to matchfacade retry with luchador facade", e);
			LuchadorFacade localFacade = (LuchadorFacade) facade;
			lua.put("__internal", localFacade);
			logger.debug("__internal assigned with luchadorfacade");
		}

		LuaFunction function = lua.getFunction(name);
		logger.info("lua function found by name:" + name + " function: " + function + " parameters: "
				+ Arrays.toString(parameter));

		if (parameter.length == 0) {
			function.call();
		} else {
			if (parameter.length == 1) {
				function.call(CoerceJavaToLua.coerce(parameter[0]));
			}
			if (parameter.length == 2) {
				function.call(CoerceJavaToLua.coerce(parameter[0]), CoerceJavaToLua.coerce(parameter[1]));
			}
			if (parameter.length == 3) {
				function.call(CoerceJavaToLua.coerce(parameter[0]), CoerceJavaToLua.coerce(parameter[1]),
						CoerceJavaToLua.coerce(parameter[2]));
			}

			if (parameter.length > 3) {
				throw new RuntimeException("Too many parameters in Lua function call: " + name);
			}

		}

	}

	public void eval(String script) throws Exception {
		lua.eval(script);
	}

	public String getString(String script) throws Exception {
		return lua.getString(script);
	}

	public int getInt(String script) throws Exception {
		return lua.getInt(script);
	}

	public double getDouble(String script) throws Exception {
		return lua.getDouble(script);
	}

	public void set(String name, Object value) throws Exception {
		lua.put(name, value);
	}

	public abstract void loadDefaultLibraries() throws Exception;

}
