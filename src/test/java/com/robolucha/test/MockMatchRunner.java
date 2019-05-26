package com.robolucha.test;

import com.robolucha.game.action.OnInitAddNPC;
import com.robolucha.game.vo.MatchInitVO;
import com.robolucha.game.vo.MatchRunStateVO;
import com.robolucha.models.Match;
import com.robolucha.monitor.ServerMonitor;
import com.robolucha.publisher.MatchStatePublisher;
import com.robolucha.publisher.MockRemoteQueue;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.runner.MatchRunner;
import com.robolucha.score.ScoreUpdater;
import com.robolucha.support.DefaultGameDefinitionFileCreator;

import io.swagger.client.model.MainGameDefinition;

public class MockMatchRunner {
	static Match match = new Match();
	static MockRemoteQueue remoteQueue = new MockRemoteQueue();
	static ServerMonitor monitor = new ServerMonitor(remoteQueue);

	static class MatchStatePublisherSilent extends MatchStatePublisher {
		public MatchStatePublisherSilent() {
			super(match, remoteQueue);
		}

		@Override
		public void update(MatchRunner matchRunner) throws Exception {
		}

		@Override
		public void publish(MatchRunStateVO vo) {
		}

	}

	public static MatchRunner build() {
		return build(1000);
	}

	public static MatchRunner build(int duration) {
		return build(duration, remoteQueue, new MatchStatePublisherSilent());
	}

	public static MatchRunner build(int duration, RemoteQueue queue) {
		return build(duration, queue, null);
	}

	public static MatchRunner build(int duration, RemoteQueue queue, MatchStatePublisher publisher) {
		// TODO: create method to populate with defautls
		MainGameDefinition gameDefinition = new MainGameDefinition();
		gameDefinition.setDuration(duration);
		ServerMonitor monitor = new ServerMonitor(queue);

		Match match = new Match();
		MatchRunner runner = new MatchRunner(gameDefinition, match, queue, monitor);
		if (publisher == null) {
			publisher = new MatchStatePublisher(match, queue);
		}

        runner.addListener(new ScoreUpdater());
		runner.setPublisher(publisher);
		return runner;
	}

	public static MatchRunner buildWithDefaultLuchador() {
		// TODO: create method to populate with defautls
		MainGameDefinition gameDefinition = new MainGameDefinition();
		gameDefinition.setDuration(1000);

		DefaultGameDefinitionFileCreator.addGameComponent(gameDefinition);

		Match match = new Match();
		MatchRunner runner = new MatchRunner(gameDefinition, match, remoteQueue, monitor);

		runner.setPublisher(new MatchStatePublisherSilent());

		runner.addListener(new OnInitAddNPC());
		runner.getOnInit().onNext(new MatchInitVO(System.currentTimeMillis()));
		runner.getOnInit().onComplete();

		return runner;
	}

	public static void start(MatchRunner runner) {
		Thread t = new Thread(runner);
		t.start();
		// runner.getOnMatchStart().blockingFirst();
	}

}
