package com.robolucha.monitor;

import com.robolucha.publisher.RemoteQueue;

public class ServerMonitor {

	private RemoteQueue queue;
	private String heatBeatPreffix = "monitor.heartbeat.";
	private String metricPreffix = "monitor.metric.";

	public ServerMonitor(RemoteQueue queue) {
		this.queue = queue;
	}

	public void heartBeat(String name) {
		queue.publish(heatBeatPreffix + name, 1);
	}

	public void metric(String name, int value) {
		queue.publish(metricPreffix + name, value);
	}
}
