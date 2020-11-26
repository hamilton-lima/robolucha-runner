package com.robolucha.test;

import java.util.List;

import com.robolucha.models.MatchScore;
import com.robolucha.runner.IMatchRunnerAPI;

import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelGameDefinition;
import io.swagger.client.model.ModelMatch;
import io.swagger.client.model.ModelMatchMetric;

public class MockMatchRunnerAPI implements IMatchRunnerAPI{

	@Override
	public void addScores(List<MatchScore> scores) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addMatchParticipant(int matchId, ModelGameComponent luchador, Integer teamId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endMatch(ModelMatch match) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ModelGameComponent findLuchadorById(Integer luchadorID, Integer gamedefinitionID) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelGameDefinition getGameDefinition(String gameDefinitionName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelMatch findMatch(Integer matchID) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelMatch matchIsRunning(Integer matchID) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelGameDefinition getGameDefinition(Integer gameDefinitionID) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMatchMetric(ModelMatchMetric body) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
