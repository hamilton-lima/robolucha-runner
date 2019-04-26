package com.robolucha.runner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.robolucha.models.Code;
import com.robolucha.models.GameComponent;
import com.robolucha.models.GameDefinition;
import com.robolucha.models.Luchador;
import com.robolucha.models.Match;
import com.robolucha.models.MatchParticipant;
import com.robolucha.models.MatchScore;
import com.robolucha.shared.JSONFormat;

import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.MainCode;
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
			mainScore.luchadorID(score.getGameComponent().getId().intValue());
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
		participant.setLuchadorID(matchParticipant.getLuchador().getId().intValue());
		apiInstance.internalMatchParticipantPost(participant);
	}

	public Match createMatch(GameDefinition gameDefinition) throws Exception {
		MainMatch match = new MainMatch();
		match.setDuration(gameDefinition.getDuration());
		match.setTimeStart(JSONFormat.now());
		match.setMinParticipants(gameDefinition.getMinParticipants());
		match.setMaxParticipants(gameDefinition.getMaxParticipants());
		match.setArenaWidth(gameDefinition.getArenaWidth());
		match.setArenaHeight(gameDefinition.getArenaHeight());
		match.setBulletSize(gameDefinition.getBulletSize());
		match.setLuchadorSize(gameDefinition.getLuchadorSize());
		match.setFps(gameDefinition.getFps());
		match.setBuletSpeed(gameDefinition.getBuletSpeed());

		Iterator<GameComponent> iterator = gameDefinition.getGameComponents().iterator();
		while (iterator.hasNext()) {
			GameComponent component = iterator.next();
			MainLuchador participant = new MainLuchador();
			participant.setName(component.getName());
			participant.setCodes(convertCodes(component.getCodes()));

			MainLuchador updatedComponent = apiInstance.internalGameComponentPost(participant);
			updateGameDefinitionWithID(gameDefinition, updatedComponent);
		}

		match = apiInstance.internalMatchPost(match);
		logger.info("createMatch() API response" + match);

		Match result = new Match();
		BeanUtils.copyProperties(result, match);
		logger.info("createMatch()" + result);
		return result;
	}

	private void updateGameDefinitionWithID(GameDefinition gameDefinition, MainLuchador updatedComponent) {
		for (GameComponent component : gameDefinition.getGameComponents()) {
			if (component.getName().equals(updatedComponent.getName())) {
				component.setId(updatedComponent.getId());
				logger.info("Updated gamedefinition gamecomponent, ID:" + component.getId() + " name:"
						+ component.getName());
			}
		}
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

	public Luchador findLuchadorById(Long luchadorID) throws Exception {
		MainLuchador luchadorFromAPI = apiInstance.internalLuchadorGet(luchadorID.intValue());
		return mapLuchadorAPI2Bean(luchadorFromAPI);
	}

	public Luchador mapLuchadorAPI2Bean(MainLuchador input) throws Exception {
		// build luchador
		Luchador luchador = new Luchador();
		luchador.setId(input.getId());
		luchador.setName(input.getName());

		if (input.getCodes() != null) {
			// copy code objects
			Iterator<MainCode> iterator = input.getCodes().iterator();
			while (iterator.hasNext()) {
				MainCode codeFromAPI = iterator.next();
				Code code = new Code();
				BeanUtils.copyProperties(code, codeFromAPI);
				luchador.getCodes().add(code);
			}
		}

		return luchador;
	}

}
