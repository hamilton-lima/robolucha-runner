package com.robolucha.runner;

import com.robolucha.monitor.ServerMonitor;

import io.swagger.client.model.ModelMatchMetric;

public class MatchRunnerMonitor {
	private ServerMonitor serverMonitor;
	private ModelMatchMetric metric;
	private int players;
	private int frames;
	private long monitorLastTime;
	private int seconds;
	private int interval;

	public MatchRunnerMonitor(ServerMonitor serverMonitor, ModelMatchMetric metric, int interval) {
		this.serverMonitor = serverMonitor;
		this.metric = metric;
		this.interval = interval;

		seconds = interval / 1000;
		if (seconds <= 0) {
			seconds = 1;
		}

		players = 0;
		resetCounters();
	}

	public void tick() {
		frames++;

		if ((System.currentTimeMillis() - monitorLastTime) > interval) {
			updateMetric();
			sendMetric();
			resetCounters();
		}
	}

	private void resetCounters() {
		monitorLastTime = System.currentTimeMillis();
		frames = 0;
	}

	public void addPlayer() {
		players++;
	}

	private void updateMetric() {
		metric.setFps(frames / seconds);
		metric.setPlayers(players);
	}

	private void sendMetric() {
		serverMonitor.matchMetric(metric);
	}
}
