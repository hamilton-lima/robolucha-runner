package com.robolucha.runner;

import com.robolucha.monitor.ServerMonitor;

import io.swagger.client.model.MainMatchMetric;

public class MatchRunnerMonitor {

	private static final int MONITOR_INTERVAL = 10000;
	private ServerMonitor serverMonitor;
	private MainMatchMetric metric;
	private int playerCount = 0;
	private int frameCount = 0;
	private int publisherCount = 0;
	private long monitorLastTime = 0;
	
	public MatchRunnerMonitor(ServerMonitor serverMonitor, MainMatchMetric metric) {
		this.serverMonitor = serverMonitor;
		this.metric = metric;
	}

	public void tick() {
		if ((System.currentTimeMillis() - monitorLastTime) > MONITOR_INTERVAL) {
			updateMetric();
			sendMetric();
			resetCounters();
		}
	}

	private void resetCounters() {
		monitorLastTime = System.currentTimeMillis();
		playerCount = 0;
		frameCount = 0;
		publisherCount = 0;
	}

	private void sendMetric() {
		// TODO Auto-generated method stub
		
	}

	private void updateMetric() {
		// TODO Auto-generated method stub
		
	}

	public void addPlayer() {
		playerCount ++;
	}
		
}
