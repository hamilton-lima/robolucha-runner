package com.robolucha.runner;

import com.robolucha.monitor.ServerMonitor;

import io.swagger.client.model.MainMatchMetric;

public class MatchRunnerMonitor {

	private static final int MONITOR_INTERVAL = 10000;
	private ServerMonitor serverMonitor;
	private MainMatchMetric metric;
	private int players;
	private int frames;
	private long monitorLastTime;
	private int seconds;

	public MatchRunnerMonitor(ServerMonitor serverMonitor, MainMatchMetric metric) {
		this.serverMonitor = serverMonitor;
		this.metric = metric;

		seconds = MONITOR_INTERVAL / 1000;
		players = 0;
		frames = 0;
		monitorLastTime = 0;
	}

	public void tick() {
		frames++;

		if ((System.currentTimeMillis() - monitorLastTime) > MONITOR_INTERVAL) {
			updateMetric();
			sendMetric();
			resetCounters();
		}
	}

	private void resetCounters() {
		monitorLastTime = System.currentTimeMillis();
		players = 0;
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
