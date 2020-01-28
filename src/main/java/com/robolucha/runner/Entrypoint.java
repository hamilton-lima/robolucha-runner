package com.robolucha.runner;

import java.util.Arrays;

import org.apache.log4j.Logger;

import com.robolucha.listener.EndMatchListener;
import com.robolucha.listener.JoinMatchListener;
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

	public static void main(String[] args) throws Exception {

		logger.info("Starting robolucha-runner, " + Arrays.toString(args));

		if (args.length < 1) {
			String message = "Invalid use, must provide: server id";
			throw new RuntimeException(message);
		}

		String serverID = args[0];

		addRunTimeHook();
		configAPIClient();

		ThreadMonitor threadMonitor = ThreadMonitor.getInstance();
		RemoteQueue queue = new RemoteQueue(Config.getInstance());
		ServerMonitor monitor = new ServerMonitor(queue);

		Server server = new Server(serverID, threadMonitor, queue, monitor);
		StartMatchListener.listen(server);
		EndMatchListener.listen(server);
		JoinMatchListener.listen(server);

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
