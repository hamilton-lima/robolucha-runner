package com.robolucha.runner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;

import com.robolucha.shared.JSONFormat;

import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelJoinMatch;

public class JoinMatchQueue implements Runnable {

	static Logger logger = Logger.getLogger(JoinMatchQueue.class);
	private static final long SLEEP = 100;

	private HashMap<Integer, MatchRunner> matches;
	private Queue<ModelJoinMatch> queue;
	private boolean alive = true;

	JoinMatchQueue() {
		this.matches = new HashMap<Integer, MatchRunner>();
		this.queue = new LinkedList<ModelJoinMatch>();
	}

	public void add(Integer matchID, MatchRunner runner) {
		this.matches.put(matchID, runner);
	}

	public void add(ModelJoinMatch joinMatch) {
		this.queue.add(joinMatch);
	}

	@Override
	public void run() {
		while (alive) {
			ModelJoinMatch next = this.queue.poll();
			try {
				if (next != null) {
					// match is running
					if (this.matches.containsKey(next.getMatchID())) {
						addLuchadorToMatch(next);
					} else {
						// add back to the queue
						this.queue.add(next);
					}
				}

				Thread.sleep(SLEEP);
			} catch (Exception e) {
				logger.error("Interrupted while waiting", e);
			}
		}
	}

	private void addLuchadorToMatch(ModelJoinMatch next) throws Exception {
		MatchRunner runner = this.matches.get(next.getMatchID());
		logger.info(">>>>>>>> found Runner " + JSONFormat.clean(runner.toString()));

		int gameDefinitionID = runner.getGameDefinition().getId();
		ModelGameComponent luchador = MatchRunnerAPI.getInstance().findLuchadorById(next.getLuchadorID(),
				gameDefinitionID);

		logger.info(">>>>>>>> Luchador found by ID " + JSONFormat.clean(luchador.toString()));
		runner.addLuchador(luchador);
	}

	public void stop() {
		this.alive = false;
	}

	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

}
