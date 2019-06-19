package com.robolucha.runner.code;

import com.robolucha.runner.MatchRunner;

public interface MatchScriptDefinition extends ScriptDefinition {
	MatchScriptFacade buildFacade(MatchRunner runner);
}
