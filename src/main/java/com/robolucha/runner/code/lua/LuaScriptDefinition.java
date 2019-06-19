package com.robolucha.runner.code.lua;

import java.util.HashMap;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import com.robolucha.runner.code.MethodDefinition;
import com.robolucha.runner.code.ScriptDefinition;
import com.robolucha.runner.code.ScriptFacade;

public abstract class LuaScriptDefinition implements ScriptDefinition {

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

		lua.put("__internal", facade);
		LuaFunction function = lua.getFunction(name);

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
