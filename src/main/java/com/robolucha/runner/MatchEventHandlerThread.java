package com.robolucha.runner;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;

import com.robolucha.event.ConstEvents;
import com.robolucha.event.GeneralEvent;
import com.robolucha.game.event.MatchEventListener;
import com.robolucha.monitor.ServerMonitor;

public class MatchEventHandlerThread implements Runnable {

	private static final long SLEEP = 5;

	static Logger logger = Logger.getLogger(MatchEventHandlerThread.class);

	private MatchEventHandler matchEventHandler;
	private Queue<GeneralEvent> events;
	private String name;
	private boolean alive;
	private Object[] eventListeners;

	private ServerMonitor monitor;

	public MatchEventHandlerThread(MatchEventHandler matchEventHandler, String name, ServerMonitor monitor) {
		this.matchEventHandler = matchEventHandler;
		this.events = new LinkedList<GeneralEvent>();
		this.name = name;
		this.monitor = monitor;
		this.alive = true;
	}

	// cleanup last events.
	public void kill() {
		logger.info("KILL MatchEventHandlerThread !!");
		this.alive = false;
	}

	public void add(GeneralEvent event) {
		logger.debug("add event: " + event);

		if (this.alive) {
			this.events.add(event);
		} else {
			throw new RuntimeException("Events processor is dead (" + name + ")");
		}
	}

	public void run() {
		MatchEventHandler.logger.info("START MatchEventHandlerThread : " + name);
		Thread.currentThread().setName(name);

		String message = "MatchEventHandler waiting for new events [" + name + "]";

		long logStart = System.currentTimeMillis();
		long logThreshold = 1000;

		while (alive | this.events.size() > 0) {

			GeneralEvent event = this.events.poll();

			if (event != null) {
				MatchEventHandler.logger.info("new event : " + event);
				consume(event);
			}

			if ((System.currentTimeMillis() - logStart) > logThreshold) {
				logStart = System.currentTimeMillis();
				monitor.heartBeat(message);
			}

			try {
				Thread.sleep(SLEEP);
			} catch (InterruptedException e) {
				logger.error("Interromperam este rapaz ocupado MatchEventHandlerThread");
			}
		}
		
		cleanup();

		MatchEventHandler.logger.info("END MatchEventHandlerThread : " + name);
	}

	private void cleanup() {
		this.matchEventHandler = null;
		this.events.clear();
		this.name = null;
		this.eventListeners = null;
	}

	private void consume(GeneralEvent event) {
		if( this.matchEventHandler.runner.getMatchEventListeners() == null ) {
			return;
		}
		
		this.eventListeners = this.matchEventHandler.runner.getMatchEventListeners().toArray();
		logger.debug("START consumindo evento : " + event);
		logger.debug("== listeners : " + eventListeners.length);

		for (int i = 0; i < eventListeners.length; i++) {
			MatchEventListener listener = (MatchEventListener) eventListeners[i];

			if (event.getAction() == ConstEvents.ACTION_DAMAGE) {
				listener.onDamage(this.matchEventHandler.runner, event.getLutchadorA(), event.getLutchadorB(),
						event.getAmount());
			}

			if (event.getAction() == ConstEvents.ACTION_KILL) {
				listener.onKill(this.matchEventHandler.runner, event.getLutchadorA(), event.getLutchadorB());
			}

			if (event.getAction() == ConstEvents.ACTION_INIT) {
				listener.onInit(this.matchEventHandler.runner);
			}

			if (event.getAction() == ConstEvents.ACTION_START) {
				listener.onStart(this.matchEventHandler.runner);
			}

			if (event.getAction() == ConstEvents.ACTION_END) {
				listener.onEnd(this.matchEventHandler.runner);
			}

			if (event.getAction() == ConstEvents.ACTION_ALIVE) {
				listener.onAlive(this.matchEventHandler.runner);
			}
		}

		if (event.getRunAfterThis() != null) {
			for (int i = 0; i < event.getRunAfterThis().length; i++) {
				event.getRunAfterThis()[i].run();
			}
		}

		logger.debug("END consumindo evento : " + event);
	}

}