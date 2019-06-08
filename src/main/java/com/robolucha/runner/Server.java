package com.robolucha.runner;

import com.robolucha.game.action.OnInitAddNPC;
import com.robolucha.listener.JoinMatchListener;
import com.robolucha.monitor.ServerMonitor;
import com.robolucha.monitor.ThreadMonitor;
import com.robolucha.publisher.MatchEventPublisher;
import com.robolucha.publisher.MatchMessagePublisher;
import com.robolucha.publisher.MatchStatePublisher;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.score.ScoreUpdater;

import io.swagger.client.model.MainGameDefinition;
import io.swagger.client.model.MainJoinMatch;
import io.swagger.client.model.MainMatch;

public class Server {

	private ServerMonitor monitor;
	private RemoteQueue queue;
	private ThreadMonitor threadMonitor;

	public Server(ThreadMonitor threadMonitor, RemoteQueue queue, ServerMonitor monitor) {
		this.threadMonitor = threadMonitor;
		this.queue = queue;
		this.monitor = monitor;
	}

	public void start(String gameDefinitionName) throws Exception {
		MainMatch match = MatchRunnerAPI.getInstance().createMatch(gameDefinitionName);
		MainGameDefinition gameDefinition = MatchRunnerAPI.getInstance().getGameDefinition(gameDefinitionName);

		MatchRunner runner = new MatchRunner(gameDefinition, match, queue, monitor);
		MatchStatePublisher publisher = new MatchStatePublisher(match, queue);

		Thread thread = setupRunner(runner, publisher);
		thread.start();
	}
	
	public void start(MainJoinMatch joinMatch) {
//		MainMatch match = MatchRunnerAPI.getInstance().findMatch(joinMatch.getMatchID());
//
//		MainGameComponent luchador = MatchRunnerAPI.getInstance().findLuchadorById(joinMatch.getLuchadorID(),
//				match.getGameDefinitionID());
//
//		logger.info(">>>>>>>> Luchador found by ID " + luchador);
//		runner.addLuchador(luchador);
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

		// join match listener
		JoinMatchListener.listen(queue, runner);

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

}
