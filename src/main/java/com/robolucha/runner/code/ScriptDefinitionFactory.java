package com.robolucha.runner.code;

import com.robolucha.runner.code.lua.LuchadorLuaScriptDefinition;
import com.robolucha.runner.code.lua.MatchLuaScriptDefinition;

public class ScriptDefinitionFactory {
	private static ScriptDefinitionFactory instance = new ScriptDefinitionFactory();

	private ScriptDefinitionFactory() {

	}

	public static ScriptDefinitionFactory getInstance() {
		return instance;
	}

	public LuchadorScriptDefinition getLuchadorScript() {
		return new LuchadorLuaScriptDefinition();
	}

	public MatchScriptDefinition getMatchScript() {
		return new MatchLuaScriptDefinition();
	}
}
