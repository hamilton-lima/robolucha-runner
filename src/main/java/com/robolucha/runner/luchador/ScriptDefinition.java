package com.robolucha.runner.luchador;

import java.util.HashMap;

import com.robolucha.runner.luchador.lua.ScriptFacade;

public interface ScriptDefinition {

	HashMap<String, MethodDefinition> getDefaultMethods();

	void afterCompile();

	void run(ScriptFacade facade, String name, Object... parameter);

	void eval(String script) throws Exception;

	String getString(String script) throws Exception;

	int getInt(String script) throws Exception;

	double getDouble(String script) throws Exception;

	void set(String name, Object value) throws Exception;

	void loadDefaultLibraries() throws Exception;

	ScriptFacade buildFacade(LuchadorRunner luchadorRunner, String codeName);

}
