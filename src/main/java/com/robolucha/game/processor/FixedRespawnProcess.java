package com.robolucha.game.processor;

import java.util.LinkedHashMap;

import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.RespawnPoint;
import com.robolucha.runner.luchador.LuchadorRunner;

public class FixedRespawnProcess implements IRespawnProcessor {

	private RespawnPoint point;

	public FixedRespawnProcess(MatchRunner runner) {
		this.point = new RespawnPoint(runner.getGameDefinition().getRespawnX(),
				runner.getGameDefinition().getRespawnY());
	}

	@Override
	public void cleanup() {
		this.point = null;
	}

	@Override
	public RespawnPoint getRespawnPoint(LuchadorRunner runner, LinkedHashMap<Integer, LuchadorRunner> runners) {
		return this.point;
	}

	@Override
	public RespawnPoint[] getLocations() {
		return new RespawnPoint[] { point };
	}

}
