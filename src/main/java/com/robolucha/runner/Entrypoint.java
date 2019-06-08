package com.robolucha.runner;

import java.util.Arrays;

import org.apache.log4j.Logger;

import com.robolucha.game.action.OnInitAddNPC;
import com.robolucha.listener.JoinMatchListener;
import com.robolucha.listener.StartMatchListener;
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
import io.swagger.client.model.MainGameDefinition;

public class Entrypoint {

	static Logger logger = Logger.getLogger(Entrypoint.class);
	MatchMessagePublisher matchMessagePublisher;
	static final String GAME_MODE_MULTIPLAYER = "multiplayer";
	static final String GAME_MODE_TUTORIAL = "tutorial";

	static String[] gameModes = { GAME_MODE_MULTIPLAYER, GAME_MODE_TUTORIAL };

	public static void main(String[] args) throws Exception {

		if (args.length < 1) {
			String message = "Invalid use, must provide: "
					+ "server mode:[multiplayer,tutorial] and GameDefinition name if mode is multiplayer";
			throw new RuntimeException(message);
		}

		String gameMode = args[0];

		if (!Arrays.asList(gameModes).contains(gameMode)) {
			throw new RuntimeException("Invalid game mode" + gameMode);
		}

		addRunTimeHook();
		configAPIClient();

		ThreadMonitor threadMonitor = ThreadMonitor.getInstance();
		RemoteQueue queue = new RemoteQueue(Config.getInstance());
		ServerMonitor monitor = new ServerMonitor(queue);

		Server server = new Server(threadMonitor, queue, monitor);

		if (gameMode.contentEquals(GAME_MODE_MULTIPLAYER)) {

			if (args.length < 2) {
				throw new RuntimeException("Missing Gamedefinition name for game mode: " + gameMode);
			}

			String gameDefinitionName = args[1];
			server.start(gameDefinitionName);
		}

		if (gameMode.contentEquals(GAME_MODE_TUTORIAL)) {
			StartMatchListener.listen(server);
		}

	}

	private static void addRunTimeHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				ThreadMonitor.getInstance().contextDestroyed();
			}
		});
	}

	public static void configAPIClient() {
		ApiClient apiClient = new ApiClient();
		apiClient.setBasePath(Config.getInstance().getBasePath());
		apiClient.addDefaultHeader("Authorization", Config.getInstance().getInternalAPIKey());

		Configuration.setDefaultApiClient(apiClient);
	}
}
