package com.robolucha.runner;

import java.util.List;

import org.apache.log4j.Logger;

import com.robolucha.models.MatchParticipant;
import com.robolucha.models.MatchScore;
import com.robolucha.shared.JSONFormat;

import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.MainFindLuchadorWithGamedefinition;
import io.swagger.client.model.MainGameComponent;
import io.swagger.client.model.MainGameDefinition;
import io.swagger.client.model.MainMatch;
import io.swagger.client.model.MainMatchParticipant;
import io.swagger.client.model.MainMatchScore;
import io.swagger.client.model.MainScoreList;

public class MatchRunnerAPI {

	private Logger logger = Logger.getLogger(MatchRunnerAPI.class);
	private DefaultApi apiInstance;

	private MatchRunnerAPI() {
		apiInstance = new DefaultApi();
	}

	private static MatchRunnerAPI instance = new MatchRunnerAPI();

	public static MatchRunnerAPI getInstance() {
		return instance;
	}

	public void addScores(List<MatchScore> scores) throws Exception {

		MainScoreList requestBody = new MainScoreList();

		for (MatchScore score : scores) {
			MainMatchScore mainScore = new MainMatchScore();
			mainScore.setMatchID(score.getMatchRun().getId().intValue());
			mainScore.luchadorID(Long.valueOf(score.getGameComponent().getId()).intValue());
			mainScore.setKills(score.getKills());
			mainScore.setDeaths(score.getDeaths());
			mainScore.setScore(score.getScore());
			requestBody.addScoresItem(mainScore);
		}

		apiInstance.internalAddMatchScoresPost(requestBody);
	}

	public void addMatchParticipant(MatchParticipant matchParticipant) throws Exception {
		MainMatchParticipant participant = new MainMatchParticipant();
		participant.setMatchID(matchParticipant.getMatchRun().getId().intValue());
		participant.setLuchadorID(Long.valueOf(matchParticipant.getLuchador().getId()).intValue());
		apiInstance.internalMatchParticipantPost(participant);
	}

	public MainMatch createMatch(String gameDefinitionName) throws Exception {
		MainMatch match = apiInstance.internalStartMatchNamePost(gameDefinitionName);
		logger.info("createMatch() API response" + match);
		return match;
	}

	public void endMatch(MainMatch match) throws Exception {
		MainMatch body = new MainMatch();
		body.setId(match.getId().intValue());
		body.setTimeEnd(JSONFormat.now());

		apiInstance.internalEndMatchPut(body);
	}

	public MainGameComponent findLuchadorById(Integer luchadorID, Integer gamedefinitionID) throws Exception {
		
		MainFindLuchadorWithGamedefinition body = new MainFindLuchadorWithGamedefinition();
		body.luchadorID(luchadorID);
		body.gameDefinitionID(gamedefinitionID);
		
		MainGameComponent component = apiInstance.internalLuchadorPost(body);
		return component;
	}

	public MainGameDefinition getGameDefinition(String gameDefinitionName) throws Exception {
		MainGameDefinition gamedefinition = apiInstance.internalGameDefinitionNameGet(gameDefinitionName);
		logger.info("getGameDefinition() API response" + gamedefinition);
		return gamedefinition;
	}

}
