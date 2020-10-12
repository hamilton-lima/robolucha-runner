package com.robolucha.runner;

import java.util.List;

import org.apache.log4j.Logger;

import com.robolucha.models.MatchScore;
import com.robolucha.shared.JSONFormat;

import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.ModelFindLuchadorWithGamedefinition;
import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelGameDefinition;
import io.swagger.client.model.ModelMatch;
import io.swagger.client.model.ModelMatchMetric;
import io.swagger.client.model.ModelMatchParticipant;
import io.swagger.client.model.ModelMatchScore;
import io.swagger.client.model.ModelScoreList;

public class MatchRunnerAPI {

	private Logger logger = Logger.getLogger(MatchRunnerAPI.class);
	private DefaultApi api;

	private MatchRunnerAPI() {
		api = new DefaultApi();
	}

	private static MatchRunnerAPI instance = new MatchRunnerAPI();

	public static MatchRunnerAPI getInstance() {
		return instance;
	}

	public void addScores(List<MatchScore> scores) throws Exception {

		ModelScoreList requestBody = new ModelScoreList();

		for (MatchScore score : scores) {
			ModelMatchScore mainScore = new ModelMatchScore();
			mainScore.setMatchID(score.getMatchRun().getId().intValue());
			mainScore.luchadorID(Long.valueOf(score.getGameComponent().getId()).intValue());
			mainScore.setKills(score.getKills());
			mainScore.setDeaths(score.getDeaths());
			mainScore.setScore(score.getScore());
			requestBody.addScoresItem(mainScore);
		}

		api.internalAddMatchScoresPost(requestBody);
	}

	public void addMatchParticipant(int matchId, ModelGameComponent luchador, Integer teamId) throws Exception {
		ModelMatchParticipant participant = new ModelMatchParticipant();
		participant.setMatchID(matchId);
		participant.setLuchadorID(Long.valueOf(luchador.getId()).intValue());
		api.internalMatchParticipantPost(participant);
	}

	public void endMatch(ModelMatch match) throws Exception {
		ModelMatch body = new ModelMatch();
		body.setId(match.getId().intValue());
		body.setTimeEnd(JSONFormat.now());

		api.internalEndMatchPut(body);
	}

	public ModelGameComponent findLuchadorById(Integer luchadorID, Integer gamedefinitionID) throws Exception {

		ModelFindLuchadorWithGamedefinition body = new ModelFindLuchadorWithGamedefinition();
		body.luchadorID(luchadorID);
		body.gameDefinitionID(gamedefinitionID);

		ModelGameComponent component = api.internalLuchadorPost(body);
		return component;
	}

	public ModelGameDefinition getGameDefinition(String gameDefinitionName) throws Exception {
		ModelGameDefinition gamedefinition = api.internalGameDefinitionNameGet(gameDefinitionName);
		logger.info("getGameDefinition() API response" + JSONFormat.clean(gamedefinition.toString()));
		return gamedefinition;
	}

	public ModelMatch findMatch(Integer matchID) throws Exception {
		ModelMatch result = api.internalMatchSingleGet(matchID);
		return result;
	}

	public ModelMatch matchIsRunning(Integer matchID) throws Exception {
		ModelMatch body = new ModelMatch();
		body.setId(matchID);
		ModelMatch result = api.internalRunMatchPut(body);
		return result;
	}

	public ModelGameDefinition getGameDefinition(Integer gameDefinitionID) throws Exception {
		ModelGameDefinition result = api.internalGameDefinitionIdIdGet(gameDefinitionID);
		return result;
	}

	public void addMatchMetric(ModelMatchMetric body) throws Exception {
		api.internalMatchMetricPost(body);
	}

}
