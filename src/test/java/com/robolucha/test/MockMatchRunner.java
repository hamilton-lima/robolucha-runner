package com.robolucha.test;

import com.robolucha.game.action.OnInitAddNPC;
import com.robolucha.game.vo.MatchInitVO;
import com.robolucha.game.vo.MatchRunStateVO;
import com.robolucha.models.GameDefinition;
import com.robolucha.models.Match;
import com.robolucha.publisher.MatchStatePublisher;
import com.robolucha.publisher.MockRemoteQueue;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.runner.Config;
import com.robolucha.runner.MatchRunner;
import com.robolucha.support.DefaultGameDefinitionFileCreator;

public class MockMatchRunner {
	static Match match = new Match();
	static MockRemoteQueue remoteQueue = new MockRemoteQueue();

	static class MatchStatePublisherSilent extends MatchStatePublisher {
		public MatchStatePublisherSilent() {
			super(match, new RemoteQueue(Config.getInstance()));
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
		GameDefinition gameDefinition = new GameDefinition();
		gameDefinition.setDuration(1000);

		Match match = new Match();
		MatchRunner runner = new MatchRunner(gameDefinition, match, remoteQueue);

		runner.setPublisher(new MatchStatePublisherSilent());
		return runner;
	}

	public static MatchRunner buildWithDefaultLuchador() {
		GameDefinition gameDefinition = new GameDefinition();
		gameDefinition.setDuration(1000);

		DefaultGameDefinitionFileCreator.addGameComponent(gameDefinition);

		Match match = new Match();
		MatchRunner runner = new MatchRunner(gameDefinition, match, remoteQueue);

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
