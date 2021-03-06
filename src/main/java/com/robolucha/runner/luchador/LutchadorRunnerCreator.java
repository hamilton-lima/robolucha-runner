package com.robolucha.runner.luchador;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;

import com.robolucha.listener.LuchadorUpdateListener;
import com.robolucha.monitor.ServerMonitor;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.runner.MatchRunner;
import com.robolucha.shared.JSONFormat;

import io.reactivex.subjects.PublishSubject;
import io.swagger.client.model.ModelGameComponent;

/**
 * 
 * @author Hamilton Lima
 *
 */
public class LutchadorRunnerCreator implements Runnable {

	private class ToBeCreated {
		PublishSubject<LuchadorRunner> subject;
		ModelGameComponent component;
		Integer teamId;
	}

	static Logger logger = Logger.getLogger(LutchadorRunnerCreator.class);
	private static final long SLEEP = 5;

	private MatchRunner owner;
	private Queue<ToBeCreated> gameComponents;
	private Thread thread;
	private boolean alive;
	private String name;
	private RemoteQueue queue;
	private ServerMonitor monitor;

	// private GameComponent gameComponent;

	public LutchadorRunnerCreator(MatchRunner owner, RemoteQueue queue, ServerMonitor monitor) {
		this.owner = owner;
		this.queue = queue;
		this.monitor = monitor;

		this.alive = true;
		this.gameComponents = new LinkedList<ToBeCreated>();
		this.name = "LutchadorRunnerCreator-Thread-" + owner.getMatch().getId();

		thread = new Thread(this);
		thread.start();
	}

	public void cleanup() {
		this.alive = false;
	}

	public PublishSubject<LuchadorRunner> add(ModelGameComponent component, Integer teamId) {
		logger.info("gamecomponent added to creation queue: " + JSONFormat.clean(component.toString()));

		ToBeCreated toCreate = new ToBeCreated();
		toCreate.component = component;
		toCreate.teamId = teamId;
		toCreate.subject = PublishSubject.create();

		gameComponents.add(toCreate);
		return toCreate.subject;
	}

	public void run() {
		Thread.currentThread().setName(name);
		String message = name + " waiting for new luchadores to create.";

		long logStart = System.currentTimeMillis();
		long logThreshold = 10000;

		while (alive) {

			ToBeCreated toCreate = gameComponents.poll();
			if (toCreate != null && toCreate.component != null) {
				try {
					LuchadorRunner runner = create(toCreate.component, toCreate.teamId);
					toCreate.subject.onNext(runner);
					toCreate.subject.onComplete();
				} catch (Exception e) {
					logger.error("Error creating component", e);
				}

				if (gameComponents.isEmpty()) {
					logger.info("Nothing else to create, My job here is done.");
				}
			}

			if ((System.currentTimeMillis() - logStart) > logThreshold) {
				logStart = System.currentTimeMillis();
				monitor.heartBeat(message);
			}

			try {
				Thread.sleep(SLEEP);
			} catch (InterruptedException e) {
				logger.error("I sense an interruption in the force...", e);
			}

		}

		gameComponents = null;

	}

	private LuchadorRunner create(ModelGameComponent component, Integer teamId) throws Exception {

		if (logger.isInfoEnabled()) {
			logger.info("gamecomponent started (run): " + JSONFormat.clean(component.toString()));
		}

		if (owner.getMatch().getId() != null) {
			this.owner.getApi().addMatchParticipant(owner.getMatch().getId().intValue(), component, teamId);
		} else {
			logger.warn("!!! trying to save match participation with unsaved MatchRun, is it running a TEST?");
		}

		LuchadorRunner runner = new LuchadorRunner(component, teamId, owner);
		LuchadorUpdateListener.listen(queue, runner);

		if (logger.isInfoEnabled()) {
			logger.info(">>>>>>>>> LUCHADOR runner created: " + runner.getGameComponent().getName());
		}

		if (logger.isDebugEnabled()) {
			logger.info(" runner=" + runner);
		}

		owner.getRunners().put(runner.getGameComponent().getId(), runner);
		return runner;
	}

}