package com.robolucha.runner;

import java.util.Arrays;

import org.apache.log4j.Logger;

import com.robolucha.listener.StartMatchListener;
import com.robolucha.monitor.ServerMonitor;
import com.robolucha.monitor.ThreadMonitor;
import com.robolucha.publisher.MatchMessagePublisher;
import com.robolucha.publisher.RemoteQueue;

import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;

public class Entrypoint {

	static Logger logger = Logger.getLogger(Entrypoint.class);
	MatchMessagePublisher matchMessagePublisher;
	static final String GAME_MODE_MULTIPLAYER = "multiplayer";
	static final String GAME_MODE_TUTORIAL = "tutorial";

	static String[] gameModes = { GAME_MODE_MULTIPLAYER, GAME_MODE_TUTORIAL };

	public static void main(String[] args) throws Exception {

		logger.info("Starting robolucha-runner, " + Arrays.toString(args));

		if (args.length < 2) {
			String message = "Invalid use, must provide: "
					+ "server id, server mode:[multiplayer,tutorial] and GameDefinition name if mode is multiplayer";
			throw new RuntimeException(message);
		}

		String serverID = args[0];
		String gameMode = args[1];

		if (!Arrays.asList(gameModes).contains(gameMode)) {
			throw new RuntimeException("Invalid game mode" + gameMode);
		}

		addRunTimeHook();
		configAPIClient();

		ThreadMonitor threadMonitor = ThreadMonitor.getInstance();
		RemoteQueue queue = new RemoteQueue(Config.getInstance());
		ServerMonitor monitor = new ServerMonitor(queue);

		Server server = new Server(serverID, threadMonitor, queue, monitor);

		if (gameMode.contentEquals(GAME_MODE_MULTIPLAYER)) {

			if (args.length < 3) {
				throw new RuntimeException("Missing Gamedefinition name for game mode: " + gameMode);
			}

			String gameDefinitionName = args[2];
			server.start(gameDefinitionName);
		}

		if (gameMode.contentEquals(GAME_MODE_TUTORIAL)) {
			// wait for the command to start matches
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
