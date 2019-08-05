package com.robolucha.publisher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.robolucha.event.ConstEvents;
import com.robolucha.game.event.MatchEventListener;
import com.robolucha.models.LuchadorMatchState;
import com.robolucha.models.MatchEvent;
import com.robolucha.models.MatchScore;
import com.robolucha.monitor.ThreadMonitor;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.MatchRunnerAPI;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.shared.JSONFormat;

import io.swagger.client.model.ModelMatch;

public class MatchEventPublisher implements MatchEventListener {
	private final RemoteQueue publisher;
	private final ThreadMonitor threadMonitor;
	private final String channel;

	private Logger logger = Logger.getLogger(MatchEventPublisher.class);
	private ModelMatch match;

	public MatchEventPublisher(ModelMatch match, RemoteQueue publisher, ThreadMonitor threadMonitor) {
		this.match = match;
		this.publisher = publisher;
		this.threadMonitor = threadMonitor;
		this.channel = String.format("match.%s.event", match.getId());
	}

	public void onInit(MatchRunner runner) {
		logger.debug("match INIT : " + runner.getThreadName());
		addEvent(runner, ConstEvents.INIT);
	}

	public void onStart(MatchRunner runner) {
		logger.debug("match START : " + runner.getThreadName());
		match.setTimeStart(JSONFormat.now());
		match.setLastTimeAlive(JSONFormat.now());

		try {
			addEvent(runner, ConstEvents.START);
		} catch (Exception e) {
			reportErrors(runner, "ERROR, updating match:", e);
		}

	}

	public void onEnd(MatchRunner runner) {
		logger.debug("match END : " + runner.getThreadName());

		try {
			MatchRunnerAPI.getInstance().endMatch(match);
		} catch (Exception e) {
			reportErrors(runner, "ERROR, updating END of match:", e);
		}

		try {
			logger.info("Match END saving score");
			List<MatchScore> scores = new ArrayList<MatchScore>();

			Iterator<Integer> iterator = runner.getRunners().keySet().iterator();
			while (iterator.hasNext()) {
				Object key = (Object) iterator.next();
				LuchadorRunner luchadorRunner = runner.getRunners().get(key);
				scores.add(getMatchScore(runner, luchadorRunner));
			}

			MatchRunnerAPI.getInstance().addScores(scores);
		} catch (Exception e) {
			reportErrors(runner, "ERROR, saving score:", e);
		}

		addEvent(runner, ConstEvents.END);

	}

	private MatchScore getMatchScore(MatchRunner runner, LuchadorRunner luchadorRunner) {

		if (logger.isInfoEnabled()) {
			logger.info("getMatchScore=" + luchadorRunner.getScoreVO());
		}

		MatchScore score = new MatchScore();
		score.setMatchRun(runner.getMatch());
		score.setGameComponent(luchadorRunner.getGameComponent());
		score.setKills(luchadorRunner.getScoreVO().getK());
		score.setDeaths(luchadorRunner.getScoreVO().getD());
		score.setScore(luchadorRunner.getScoreVO().getScore());
		return score;
	}

	private void reportErrors(MatchRunner runner, String message, Exception e) {
		logger.error(message, e);
		threadMonitor.addException(runner.getThreadName(), message);
	}

	public void onAlive(MatchRunner runner) {
		runner.getMatch().setLastTimeAlive(JSONFormat.now());

		try {
			threadMonitor.alive(runner.getThreadName());
		} catch (Exception e) {
			reportErrors(runner, "ERROR, updating match last alive time:", e);
		}

	}

	@Override
	public void onKill(MatchRunner runner, LuchadorMatchState luchadorA, LuchadorMatchState luchadorB) {

		logger.debug("match onKill : " + runner.getThreadName());
		addLuchadorEvent(runner, luchadorA, luchadorB, 0.0, ConstEvents.KILL);

	}

	@Override
	public void onDamage(MatchRunner runner, LuchadorMatchState luchadorA, LuchadorMatchState luchadorB,
			Double amount) {

		logger.debug("match onDamage : " + runner.getThreadName());
		addLuchadorEvent(runner, luchadorA, luchadorB, amount, ConstEvents.DAMAGE);

	}

	public void addEvent(MatchRunner runner, String name) {
		addLuchadorEvent(runner, null, null, 0.0, name);
	}

	public void addLuchadorEvent(MatchRunner runner, LuchadorMatchState luchadorA, LuchadorMatchState luchadorB,
			Double amount, String name) {

		logger.debug("match addLuchadorEvent : " + runner.getThreadName());

		MatchEvent event = new MatchEvent();
		event.setMatch(runner.getMatch());
		event.setComponentA(luchadorA == null ? 0 : luchadorA.id);
		event.setComponentB(luchadorB == null ? 0 : luchadorB.id);
		event.setAmount(amount);
		event.setTimeStart(System.currentTimeMillis());
		event.setEvent(name);

		if (logger.isDebugEnabled()) {
			logger.debug("matchrunner.id=" + runner.getMatch().getId() + " " + name + " event=" + event);
		}

		MessageEnvelope eventEnvelope = MessageEnvelope.buildEvent(event);

		publisher.publish(channel, eventEnvelope);
	}

}
