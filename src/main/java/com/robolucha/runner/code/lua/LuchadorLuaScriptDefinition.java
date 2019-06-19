package com.robolucha.runner.code.lua;

import com.robolucha.runner.code.LuchadorScriptDefinition;
import com.robolucha.runner.code.LuchadorScriptFacade;
import com.robolucha.runner.code.MethodDefinition;
import com.robolucha.runner.code.MethodNames;
import com.robolucha.runner.luchador.LuchadorFacade;
import com.robolucha.runner.luchador.LuchadorRunner;

public class LuchadorLuaScriptDefinition extends LuaScriptDefinition implements LuchadorScriptDefinition {

	public LuchadorLuaScriptDefinition() {
		super();
		addMethod(MethodNames.ON_START, "function onStart()\n", "\nend");
		addMethod(MethodNames.ON_REPEAT, "function onRepeat()\n", "\nend");
		addMethod(MethodNames.ON_HIT_WALL, "function onHitWall()\n", "\nend");
		addMethod(MethodNames.ON_HIT_OTHER, "function onHitOther(other)\n", "\nend");
		addMethod(MethodNames.ON_FOUND, "function onFound(other,chance)\n", "\nend");
		addMethod(MethodNames.ON_GOT_DAMAGE, "function onGotDamage(other,amount)\n", "\nend");
		addMethod(MethodNames.ON_LISTEN, "function onListen(other,message)\n", "\nend");
	}

	private void addMethod(String name, String start, String end) {
		methods.put(name, new MethodDefinition(name, start, end));
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
