package com.robolucha.runner;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.robolucha.game.action.OnInitAddNPC;
import com.robolucha.listener.JoinMatchListener;
import com.robolucha.monitor.ServerMonitor;
import com.robolucha.monitor.ThreadMonitor;
import com.robolucha.publisher.MatchEventPublisher;
import com.robolucha.publisher.MatchMessagePublisher;
import com.robolucha.publisher.MatchStatePublisher;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.score.ScoreUpdater;
import com.robolucha.shared.JSONFormat;

import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelGameDefinition;
import io.swagger.client.model.ModelJoinMatch;
import io.swagger.client.model.ModelMatch;

public class Server {
	static Logger logger = Logger.getLogger(Server.class);

	private ServerMonitor monitor;
	private RemoteQueue queue;
	private ThreadMonitor threadMonitor;
	private String serverID;

	// stores the match indexed by gamedefinitionID and luchadorID
	// TODO: change this structure to support matches that are running
	// there is no need to control by user the API is handling this
	private HashMap<Integer, HashMap<Integer, Long>> matches;

	public Server(String serverID, ThreadMonitor threadMonitor, RemoteQueue queue, ServerMonitor monitor) {
		this.serverID = serverID;
		this.threadMonitor = threadMonitor;
		this.queue = queue;
		this.monitor = monitor;
		this.matches = new HashMap<Integer, HashMap<Integer, Long>>();
	}

//	public void start(String gameDefinitionName) throws Exception {
//		ModelMatch match = MatchRunnerAPI.getInstance().createMatch(gameDefinitionName);
//		ModelGameDefinition gameDefinition = MatchRunnerAPI.getInstance().getGameDefinition(gameDefinitionName);
//
//		MatchRunner runner = new MatchRunner(gameDefinition, match, queue, monitor);
//
//		runner.getOnMatchEnd().subscribe(new Consumer<ModelMatch>() {
//			public void accept(ModelMatch match) throws Exception {
//				logger.info("Match ended, stopping the application, see you next time.");
//				MatchRunnerAPI.getInstance().endMatch(match);
//				System.exit(0);
//			}
//		});
//
//		MatchStatePublisher publisher = new MatchStatePublisher(serverID, match, queue);
//
//		Thread thread = setupRunner(runner, publisher);
//		thread.start();
//	}

	public synchronized void start(ModelJoinMatch joinMatch) throws Exception {
		logger.info("Start match " + joinMatch);

		ModelMatch match = MatchRunnerAPI.getInstance().findMatch(joinMatch.getMatchID());
		logger.info("found match " + JSONFormat.clean(match.toString()));

		ModelGameComponent luchador = MatchRunnerAPI.getInstance().findLuchadorById(joinMatch.getLuchadorID(),
				match.getGameDefinitionID());
		logger.info("found luchador " + JSONFormat.clean(luchador.toString()));

		// check if the combination is already running in the server
		if (!isRunning(match.getGameDefinitionID(), luchador.getId())) {
			
			// save to the list of running matches
			add2Matches(match.getGameDefinitionID(), luchador.getId());

			ModelGameDefinition gameDefinition = MatchRunnerAPI.getInstance()
					.getGameDefinition(match.getGameDefinitionID());
			logger.info("found gamedefinition " + JSONFormat.clean(gameDefinition.toString()));

			MatchRunner runner = new MatchRunner(gameDefinition, match, queue, monitor);
			runner.addLuchador(luchador);

			MatchStatePublisher publisher = new MatchStatePublisher(serverID, match, queue);

			Thread thread = setupRunner(runner, publisher);
			thread.start();
		} else {
			logger.info("Is already running: " + joinMatch);
		}

	}

	public void add2Matches(Integer gameDefinitionID, Integer luchadorID) {
		HashMap<Integer, Long> luchadorHash = this.matches.get(gameDefinitionID);
		if (luchadorHash == null) {
			luchadorHash = new HashMap<Integer, Long>();
			luchadorHash.put(luchadorID, System.currentTimeMillis());
			this.matches.put(gameDefinitionID, luchadorHash);
		} else {
			Long timestamp = luchadorHash.get(luchadorID);
			if (timestamp == null) {
				luchadorHash.put(luchadorID, System.currentTimeMillis());
			}
		}
	}

	public boolean isRunning(Integer gameDefinitionID, Integer luchadorID) {
		HashMap<Integer, Long> luchadorHash = this.matches.get(gameDefinitionID);
		if (luchadorHash == null) {
			return false;
		}

		Long timestamp = luchadorHash.get(luchadorID);
		if (timestamp == null) {
			return false;
		}

		return true;
	}

	public Thread setupRunner(MatchRunner runner, MatchStatePublisher publisher) {

		ThreadMonitor.getInstance().register(runner);

		// add NPC to the match
		runner.addListener(new OnInitAddNPC());

		// TODO: convert to subscription model
		// listener to record match events
		runner.addListener(new MatchEventPublisher(runner.getMatch(), queue, threadMonitor));

		// TODO: convert to subscription model
		// listener to update scores
		runner.addListener(new ScoreUpdater());

		// TODO: convert to subscription model
		// listener to the match state
		runner.setPublisher(publisher);

		// message listener
		runner.addListener(new MatchMessagePublisher(queue));

		return new Thread(runner);
	}

	public ServerMonitor getMonitor() {
		return monitor;
	}

	public RemoteQueue getQueue() {
		return queue;
	}

	public ThreadMonitor getThreadMonitor() {
		return threadMonitor;
	}

	public void add2JoinMatchQueue(ModelJoinMatch joinMatch) {
		// TODO add to queue
		// TODO change runner to consume this queue
		
//		ModelGameComponent luchador = MatchRunnerAPI.getInstance().findLuchadorById(joinMatch.getLuchadorID(),
//				gameDefinition.getId());
//
//		logger.info(">>>>>>>> Luchador found by ID " + JSONFormat.clean(luchador.toString()));
//		runner.addLuchador(luchador);

		
	}

}
