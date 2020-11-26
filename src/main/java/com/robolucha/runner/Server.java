package com.robolucha.runner;

import org.apache.log4j.Logger;

import com.robolucha.game.action.OnInitAddNPC;
import com.robolucha.monitor.ServerMonitor;
import com.robolucha.monitor.ThreadMonitor;
import com.robolucha.publisher.MatchCreatedPublisher;
import com.robolucha.publisher.MatchEventPublisher;
import com.robolucha.publisher.MatchMessagePublisher;
import com.robolucha.publisher.MatchStatePublisher;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.score.ScoreUpdater;
import com.robolucha.shared.JSONFormat;

import io.reactivex.functions.Consumer;
import io.swagger.client.model.ModelGameDefinition;
import io.swagger.client.model.ModelJoinMatch;
import io.swagger.client.model.ModelMatch;

public class Server {
	static Logger logger = Logger.getLogger(Server.class);

	private ServerMonitor monitor;
	private RemoteQueue queue;
	private ThreadMonitor threadMonitor;
	private JoinMatchQueue joinMatchQueue;
	private String serverID;

	public Server(String serverID, ThreadMonitor threadMonitor, RemoteQueue queue, ServerMonitor monitor) {
		this.serverID = serverID;
		this.threadMonitor = threadMonitor;
		this.queue = queue;
		this.monitor = monitor;

		this.joinMatchQueue = new JoinMatchQueue(this);
		this.joinMatchQueue.start();
	}

	public synchronized void start(ModelMatch startMatch) throws Exception {
		logger.info("Start match " + startMatch);

		ModelMatch match = MatchRunnerAPI.getInstance().findMatch(startMatch.getId());
		logger.info("found match " + JSONFormat.clean(match.toString()));

		ModelGameDefinition gameDefinition = MatchRunnerAPI.getInstance()
				.getGameDefinition(match.getGameDefinitionID());
		logger.info("found gamedefinition " + JSONFormat.clean(gameDefinition.toString()));

		MatchRunner runner = new MatchRunner(gameDefinition, match, queue, monitor, MatchRunnerAPI.getInstance());
		MatchStatePublisher publisher = new MatchStatePublisher(serverID, match, queue);

		Thread thread = setupRunner(runner, publisher);
		thread.start();
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
		runner.addListener(new MatchCreatedPublisher(queue));

		// notify when match starts to run
		runner.getOnMatchStart().subscribe(new Consumer<ModelMatch>() {
			public void accept(ModelMatch match) throws Exception {
				logger.info("Send notification that match is running: " + match.getId());
				ModelMatch result = MatchRunnerAPI.getInstance().matchIsRunning(match.getId());
				logger.info("Response from send notification that match is running: " + result.getStatus());
			}
		});

		// notify when match starts to run
		runner.getOnRunnerShutdown().subscribe(new Consumer<String>() {
			public void accept(String name) throws Exception {
				ThreadMonitor.getInstance().remove(name);
			}
		});

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
		this.joinMatchQueue.add(joinMatch);
	}

	public void end(ModelMatch endMatch) {
		logger.info("START end match" + JSONFormat.clean(endMatch.toString()));

		MatchRunner runner = getThreadMonitor().getMatch(endMatch.getId());
		if (runner == null) {
			logger.info("MatchRunner NOT found");
		} else {
			logger.info("MatchRunner to kill: " + JSONFormat.clean(runner.getMatch().toString()));
			getThreadMonitor().remove(endMatch.getId());
			runner.kill();
		}

	}

}
