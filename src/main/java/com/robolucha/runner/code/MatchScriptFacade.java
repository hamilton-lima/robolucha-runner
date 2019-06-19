package com.robolucha.runner.code;

public interface MatchScriptFacade extends ScriptFacade {
	void addDamage(int luchador, int amount);
	void endGame();
}