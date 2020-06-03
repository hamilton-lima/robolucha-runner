package com.robolucha.runner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;

import com.robolucha.shared.JSONFormat;

import io.swagger.client.model.ModelCode;
import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelJoinMatch;

public class JoinMatchQueue implements Runnable {

	static Logger logger = Logger.getLogger(JoinMatchQueue.class);
	private static final long SLEEP = 100;

//	private HashMap<Integer, MatchRunner> matches;
	private HashMap<Integer, Integer> joinMatchRetries;
	private Queue<ModelJoinMatch> queue;
	private boolean alive = true;
	private Integer maxRetries = 20;
	private Server server;

	JoinMatchQueue(Server server) {
		this.server = server;
		this.joinMatchRetries = new HashMap<Integer, Integer>();
		this.queue = new LinkedList<ModelJoinMatch>();
	}

//	public void add(Integer matchID, MatchRunner runner) {
//		this.matches.put(matchID, runner);
//	}

	public void add(ModelJoinMatch joinMatch) {
		this.queue.add(joinMatch);
	}

	private Integer retriesToJoin(Integer matchID) {
		Integer retries = joinMatchRetries.get(matchID);
		if (retries == null) {
			retries = 0;
		}
		retries++;
		joinMatchRetries.put(matchID, retries);
		return retries;
	}

	@Override
	public void run() {
		while (alive) {
			ModelJoinMatch next = this.queue.poll();
			try {
				if (next != null) {
					// match is running
					MatchRunner runner = server.getThreadMonitor().getMatch(next.getMatchID());
					if (runner != null) {
						addLuchadorToMatch(next, runner);
					} else {
						if (retriesToJoin(next.getMatchID()) > maxRetries) {
							logger.warn("Reached max retries to join match, matchID=" + next.getMatchID());

						} else {
							logger.info("Not running this match, will check again soon, matchID=" + next.getMatchID());
							this.queue.add(next);
						}

					}
				}

				Thread.sleep(SLEEP);
			} catch (Exception e) {
				logger.error("Interrupted while waiting", e);
			}
		}
	}

	private void addLuchadorToMatch(ModelJoinMatch next, MatchRunner runner) throws Exception {

		logger.info("START addLuchadorToMatch next: " + next + " runner: " + runner);

		int gameDefinitionID = runner.getGameDefinition().getId();
		ModelGameComponent luchador = MatchRunnerAPI.getInstance().findLuchadorById(next.getLuchadorID(),
				gameDefinitionID);

		// STARTS luchador with empty code when running tutorial, wait for save command
		if (GameDefinitionType.TUTORIAL.equals(runner.getGameDefinition().getType())) {
			luchador.setCodes(new ArrayList<ModelCode>());
		}

		logger.info("Luchador found by ID " + JSONFormat.clean(luchador.toString()));
		runner.addLuchador(luchador);

		logger.info("END addLuchadorToMatch");
	}

	public void stop() {
		this.alive = false;
	}

	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

}
