package com.robolucha.runner.code;

import java.util.HashMap;

import com.robolucha.runner.luchador.LuchadorRunner;

public interface ScriptDefinition {

	HashMap<String, MethodDefinition> getDefaultMethods();

	void afterCompile();

	void run(LuchadorScriptFacade facade, String name, Object... parameter);

	void eval(String script) throws Exception;

	String getString(String script) throws Exception;

	int getInt(String script) throws Exception;

	double getDouble(String script) throws Exception;

	void set(String name, Object value) throws Exception;

	void loadDefaultLibraries() throws Exception;

	LuchadorScriptFacade buildFacade(LuchadorRunner luchadorRunner, String codeName);

}
