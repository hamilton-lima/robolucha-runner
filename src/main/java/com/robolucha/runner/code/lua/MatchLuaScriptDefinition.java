package com.robolucha.runner.code.lua;

import com.robolucha.runner.MatchFacade;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.code.MatchScriptDefinition;
import com.robolucha.runner.code.MatchScriptFacade;
import com.robolucha.runner.code.MethodDefinition;
import com.robolucha.runner.code.MethodNames;

public class MatchLuaScriptDefinition extends LuaScriptDefinition implements MatchScriptDefinition {

	public MatchLuaScriptDefinition() {
		super();
		addMethod(MethodNames.ON_START, "function onStart()\n", "\nend");
		addMethod(MethodNames.ON_HIT_OTHER, "function onHitOther(other)\n", "\nend");
	}

	private void addMethod(String name, String start, String end) {
		methods.put(name, new MethodDefinition(name, start, end));
	}

	@Override
	public void loadDefaultLibraries() throws Exception {
		ScriptReader reader = new ScriptReader();
		String defaultMethods = reader.readDefinitions(this.getClass(), "match-methods.lua");
		lua.eval(defaultMethods);
	}

	@Override
	public MatchScriptFacade buildFacade(MatchRunner runner) {
		return new MatchFacade(runner);
	}

}
