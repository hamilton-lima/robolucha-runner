package com.robolucha.game.processor;

import com.robolucha.runner.MatchRunner;

import io.swagger.client.model.MainGameDefinition;

public class RespawnProcessorFactory {

	public static IRespawnProcessor get(MatchRunner runner) {
		MainGameDefinition gameDefinition = runner.getGameDefinition();
	
		if (gameDefinition.getRespawnX() > 0 && gameDefinition.getRespawnY() > 0) {
			return new FixedRespawnProcess(runner);
		} else {
			return new RespawnProcessor(runner);
		}
	}

}
