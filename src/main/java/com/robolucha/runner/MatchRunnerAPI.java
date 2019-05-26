package com.robolucha.runner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.robolucha.models.Code;
import com.robolucha.models.Luchador;
import com.robolucha.models.Match;
import com.robolucha.models.MatchParticipant;
import com.robolucha.models.MatchScore;
import com.robolucha.shared.JSONFormat;

import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.MainCode;
import io.swagger.client.model.MainGameComponent;
import io.swagger.client.model.MainGameDefinition;
import io.swagger.client.model.MainLuchador;
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

	public Match createMatch(String gameDefinitionName) throws Exception {
		MainMatch match = apiInstance.internalStartMatchNamePost(gameDefinitionName);
		logger.info("createMatch() API response" + match);

		Match result = new Match();
		BeanUtils.copyProperties(result, match);
		logger.info("createMatch()" + result);
		return result;
	}

	private List<MainCode> convertCodes(List<Code> codes) {
		List<MainCode> result = new ArrayList<MainCode>();
		Iterator<Code> iterator = codes.iterator();
		while (iterator.hasNext()) {
			Code code = iterator.next();
			MainCode insert = new MainCode();
			insert.setEvent(code.getEvent());
			insert.setScript(code.getScript());
			result.add(insert);
		}
		return result;
	}

	public void endMatch(Match match) throws Exception {
		MainMatch body = new MainMatch();
		body.setId(match.getId().intValue());
		body.setTimeEnd(JSONFormat.now());

		apiInstance.internalEndMatchPut(body);
	}

	public MainGameComponent findLuchadorById(Integer luchadorID) throws Exception {
		MainLuchador luchadorFromAPI = apiInstance.internalLuchadorGet(luchadorID);
		return mapLuchador2GameDefinition(luchadorFromAPI);
	}

	public MainGameDefinition  getGameDefinition(String gameDefinitionName) throws Exception {
		MainGameDefinition gamedefinition = apiInstance.internalGameDefinitionNameGet(gameDefinitionName);
		logger.info("getGameDefinition() API response" + gamedefinition);
		return gamedefinition;
	}
	
	public MainGameComponent mapLuchador2GameDefinition(MainLuchador luchador) throws Exception {
		MainGameComponent component = new MainGameComponent();
		BeanUtils.copyProperties(component, luchador);
		return component;
	}

}
