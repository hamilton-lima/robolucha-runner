package com.robolucha.game.processor;

import java.util.LinkedHashMap;

import com.robolucha.runner.RespawnPoint;
import com.robolucha.runner.luchador.LuchadorRunner;

public interface IRespawnProcessor {

	void cleanup();

	RespawnPoint getRespawnPoint(LuchadorRunner runner, LinkedHashMap<Integer, LuchadorRunner> runners);

	RespawnPoint[] getLocations();

}