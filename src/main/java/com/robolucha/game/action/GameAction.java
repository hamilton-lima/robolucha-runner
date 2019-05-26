package com.robolucha.game.action;

import java.util.LinkedHashMap;

import com.robolucha.runner.luchador.LuchadorRunner;

public interface GameAction {

	public String getName();
	public void run(LinkedHashMap<Integer, LuchadorRunner> runners, LuchadorRunner runner) throws Exception;

}
