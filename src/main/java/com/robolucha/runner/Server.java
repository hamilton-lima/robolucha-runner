package com.robolucha.runner;

import org.apache.log4j.Logger;

import com.robolucha.game.action.OnInitAddNPC;
import com.robolucha.listener.JoinMatchListener;
import com.robolucha.models.GameDefinition;
import com.robolucha.models.Match;
import com.robolucha.monitor.ServerMonitor;
import com.robolucha.monitor.ThreadMonitor;
import com.robolucha.publisher.MatchEventPublisher;
import com.robolucha.publisher.MatchMessagePublisher;
import com.robolucha.publisher.MatchStatePublisher;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.score.ScoreUpdater;

import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;

/*
 * Runs a Match based on the input MatchDefinition ID
 */
public class Server {

	static Logger logger = Logger.getLogger(Server.class);
	MatchMessagePublisher matchMessagePublisher;

	public static void main(String[] args) throws Exception {

		if (args.length < 1) {
			throw new RuntimeException("Invalid use, must provide GameDefinition name (1)");
		}

		addRunTimeHook();
		configAPIClient();

		String gameDefinitionName = args[0];
		ThreadMonitor threadMonitor = ThreadMonitor.getInstance();

		RemoteQueue queue = new RemoteQueue(Config.getInstance());
		ServerMonitor monitor = new ServerMonitor(queue);
		Match match = MatchRunnerAPI.getInstance().createMatch(gameDefinitionName);
		GameDefinition gameDefinition = MatchRunnerAPI.getInstance().getGameDefinition(gameDefinitionName);

		MatchRunner runner = new MatchRunner(gameDefinition, match, queue, monitor);
		MatchStatePublisher publisher = new MatchStatePublisher(match, queue);

		Thread thread = buildRunner(runner, queue, threadMonitor, publisher, monitor);
		thread.start();

	}

	private static void addRunTimeHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				ThreadMonitor.getInstance().contextDestroyed();
			}
		});
	}

	public static Thread buildRunner(MatchRunner runner, RemoteQueue queue, ThreadMonitor threadMonitor,
			MatchStatePublisher publisher, ServerMonitor monitor) {
		
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

	public static void configAPIClient() {
		ApiClient apiClient = new ApiClient();
		apiClient.setBasePath(Config.getInstance().getBasePath());
		apiClient.addDefaultHeader("Authorization", Config.getInstance().getInternalAPIKey());

		Configuration.setDefaultApiClient(apiClient);
	}
}
