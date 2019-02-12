package com.robolucha.runner.luchador.lua;

import java.util.HashMap;

import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.runner.luchador.MethodDefinition;
import com.robolucha.runner.luchador.MethodNames;
import com.robolucha.runner.luchador.ScriptDefinition;

public class LuaScriptDefinition implements ScriptDefinition {

	private HashMap<String, MethodDefinition> methods;
	private LuaVM lua;

	@Override
	public HashMap<String, MethodDefinition> getDefaultMethods() {
		return methods;
	}

	public LuaScriptDefinition() {
		methods = new HashMap<String, MethodDefinition>();

		addMethod(MethodNames.ON_START, "function onStart()\n", "\nend");
		addMethod(MethodNames.ON_REPEAT, "function onRepeat()\n", "\nend");
		addMethod(MethodNames.ON_HIT_WALL, "function onHitWall()\n", "\nend");
		addMethod(MethodNames.ON_HIT_OTHER, "function onHitOther(other)\n", "\nend");
		addMethod(MethodNames.ON_FOUND, "function onFound(other,chance)\n", "\nend");
		addMethod(MethodNames.ON_GOT_DAMAGE, "function onGotDamage(other,amount)\n", "\nend");
		addMethod(MethodNames.ON_LISTEN, "function onListen(other,message)\n", "\nend");

		lua = new LuaVM();
	}

	private void addMethod(String name, String start, String end) {
		methods.put(name, new MethodDefinition(name, start, end));
	}

	@Override
	public void afterCompile() {

	}

	@Override
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

	@Override
	public void set(String name, Object value) throws Exception {
		lua.put(name, value);
	}

	@Override
	public void loadDefaultLibraries() throws Exception {
		ScriptReader reader = new ScriptReader();
		
		String defaultMethods = reader.readDefinitions(this.getClass(), "default-methods.lua");
		String nmsColors = reader.readDefinitions(this.getClass(), "nmscolor.lua");

		lua.eval(defaultMethods);
		lua.eval(nmsColors);
	}

	@Override
	public ScriptFacade buildFacade(LuchadorRunner luchadorRunner, String codeName) {
		return new LuaFacade(luchadorRunner, codeName);
	}

}
