package com.robolucha.runner;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

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
import io.swagger.client.model.MainMatch;

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

	public LuchadorCoach findCoach(String facebookID) {
		// TODO Auto-generated method stub
		return null;
	}

	public LuchadorCoach addCoach(String name, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	public Luchador addLuchador(Luchador luchador) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveMask(GameComponent gameComponent, MaskConfig mask) {
		// TODO Auto-generated method stub

	}

	public void addMatchParticipant(MatchParticipant matchParticipant) {
		// TODO Auto-generated method stub

	}

	public MaskConfig findMask(GameComponent gameComponent) {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO implement API call
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
		
		//TODO: make API call
	}

	public MatchScore findScore(Match match, GameComponent gameComponent) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateScore(MatchScore score) throws Exception {
		// TODO Auto-generated method stub

	}

	public void addScore(MatchScore score) throws Exception {
		// TODO Auto-generated method stub

	}

	public Match findMatchById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
