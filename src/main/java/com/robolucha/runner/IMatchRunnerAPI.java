package com.robolucha.runner;

import java.util.List;

import com.robolucha.models.MatchScore;

import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelGameDefinition;
import io.swagger.client.model.ModelMatch;
import io.swagger.client.model.ModelMatchMetric;

public interface IMatchRunnerAPI {

	void addScores(List<MatchScore> scores) throws Exception;

	void addMatchParticipant(int matchId, ModelGameComponent luchador, Integer teamId) throws Exception;

	void endMatch(ModelMatch match) throws Exception;

	ModelGameComponent findLuchadorById(Integer luchadorID, Integer gamedefinitionID) throws Exception;

	ModelGameDefinition getGameDefinition(String gameDefinitionName) throws Exception;

	ModelMatch findMatch(Integer matchID) throws Exception;

	ModelMatch matchIsRunning(Integer matchID) throws Exception;

	ModelGameDefinition getGameDefinition(Integer gameDefinitionID) throws Exception;

	void addMatchMetric(ModelMatchMetric body) throws Exception;

}