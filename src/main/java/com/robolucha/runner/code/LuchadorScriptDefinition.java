package com.robolucha.runner.code;

import com.robolucha.runner.luchador.LuchadorRunner;

public interface LuchadorScriptDefinition extends ScriptDefinition {

	LuchadorScriptFacade buildFacade(LuchadorRunner luchadorRunner, String codeName);

}
