package com.robolucha.runner.subscribers;

import com.robolucha.runner.MatchRunnerAPI;

import io.reactivex.functions.Consumer;
import io.swagger.client.model.ModelMatch;

public class MatchRunning implements Consumer<ModelMatch> {

	@Override
	public void accept(ModelMatch match) throws Exception {
		MatchRunnerAPI.getInstance().matchIsRunning(match.getId());
	}

}
