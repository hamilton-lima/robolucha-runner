package com.robolucha.runner;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.robolucha.models.Code;
import com.robolucha.models.GameComponent;
import com.robolucha.models.GameDefinition;
import com.robolucha.models.Luchador;
import com.robolucha.models.LuchadorCoach;
import com.robolucha.models.MaskConfig;
import com.robolucha.models.Match;
import com.robolucha.models.MatchParticipant;
import com.robolucha.models.MatchScore;
import com.robolucha.shared.JSONFormat;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.MainCode;
import io.swagger.client.model.MainLuchador;
import io.swagger.client.model.MainMatch;
import io.swagger.client.model.MainMatchParticipant;

//TODO: MUST DO - Implement the API calls!!!
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

	public void saveMask(GameComponent gameComponent, MaskConfig mask) {
		// throw new RuntimeException("not implemented");
	}

	public MaskConfig findMask(GameComponent gameComponent) {
		return null;
		// throw new RuntimeException("not implemented");
	}

	public MatchScore findScore(Match match, GameComponent gameComponent) throws Exception {
		throw new RuntimeException("not implemented");
	}

	public void updateScore(MatchScore score) throws Exception {
		throw new RuntimeException("not implemented");
	}

	public void addScore(MatchScore score) throws Exception {
		throw new RuntimeException("not implemented");
	}

	public void addMatchParticipant(MatchParticipant matchParticipant) throws Exception {
		MainMatchParticipant participant = new MainMatchParticipant();
		participant.setMatchID(matchParticipant.getMatchRun().getId().intValue());
		participant.setLuchadorID((int) matchParticipant.getLuchador().getId());
		apiInstance.internalMatchParticipantPost(participant);
	}

	public Match createMatch(GameDefinition gameDefinition) throws Exception {
		MainMatch match = new MainMatch();
		match.setDuration(gameDefinition.getDuration());
		match.setTimeStart(JSONFormat.now());

		match = apiInstance.internalMatchPost(match);
		logger.info("createMatch() API response" + match);

		Match result = new Match();
		BeanUtils.copyProperties(result, match);
		logger.info("createMatch()" + result);
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
