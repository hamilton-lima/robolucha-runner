package com.robolucha.monitor;

import org.apache.log4j.Logger;

import com.robolucha.publisher.RemoteQueue;
import com.robolucha.runner.MatchRunnerAPI;

import io.swagger.client.model.ModelMatchMetric;

public class ServerMonitor {

	private RemoteQueue queue;
	private String heatBeatPreffix = "monitor.heartbeat.";
	static Logger logger = Logger.getLogger(ServerMonitor.class);

	public ServerMonitor(RemoteQueue queue) {
		this.queue = queue;
	}

	public void heartBeat(String name) {
		queue.publish(heatBeatPreffix + name, 1);
	}

	public void matchMetric(ModelMatchMetric metric) {
		new Thread(() -> {
			try {
				MatchRunnerAPI.getInstance().addMatchMetric(metric);
			} catch (Exception e) {
				logger.error("Error saving match metric", e);
			}
		}).start();
	}
}
