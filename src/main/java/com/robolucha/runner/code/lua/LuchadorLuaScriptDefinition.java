package com.robolucha.runner.code.lua;

import com.robolucha.runner.code.LuchadorScriptDefinition;
import com.robolucha.runner.code.LuchadorScriptFacade;
import com.robolucha.runner.luchador.LuchadorFacade;
import com.robolucha.runner.luchador.LuchadorRunner;

public class LuchadorLuaScriptDefinition extends LuaScriptDefinition implements LuchadorScriptDefinition {

	public LuchadorLuaScriptDefinition() {
		super();
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
	public LuchadorScriptFacade buildFacade(LuchadorRunner luchadorRunner, String codeName) {
		return new LuchadorFacade(luchadorRunner, codeName);
	}

}
