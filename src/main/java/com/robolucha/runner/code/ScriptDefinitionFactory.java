package com.robolucha.runner.code;

import com.robolucha.runner.code.lua.LuaScriptDefinition;

public class ScriptDefinitionFactory {
	private static ScriptDefinitionFactory instance = new ScriptDefinitionFactory();

	private ScriptDefinitionFactory() {

	}

	public static ScriptDefinitionFactory getInstance() {
		return instance;
	}

	public ScriptDefinition getDefault() {
		return new LuaScriptDefinition();
	}
}
