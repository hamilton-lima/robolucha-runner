package com.robolucha.test;

import java.util.ArrayList;

import com.robolucha.game.action.OnInitAddNPC;
import com.robolucha.game.vo.MatchInitVO;
import com.robolucha.game.vo.MatchRunStateVO;
import com.robolucha.monitor.ServerMonitor;
import com.robolucha.publisher.MatchStatePublisher;
import com.robolucha.publisher.MockRemoteQueue;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.score.ScoreUpdater;

import io.swagger.client.model.ModelCode;
import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelGameDefinition;
import io.swagger.client.model.ModelMatch;
import io.swagger.client.model.ModelSceneComponent;

public class MockMatchRunner {
	static ModelMatch match = new ModelMatch();
	static MockRemoteQueue remoteQueue = new MockRemoteQueue();
	static ServerMonitor monitor = new ServerMonitor(remoteQueue);

	static class MatchStatePublisherSilent extends MatchStatePublisher {
		public MatchStatePublisherSilent() {
			super("mock-test", match, remoteQueue);
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

	public static MatchRunner build(ModelGameDefinition gameDefinition) {
		return build(remoteQueue, new MatchStatePublisherSilent(), gameDefinition);
	}

	public static MatchRunner build(int duration, RemoteQueue queue, MatchStatePublisher publisher) {
		ModelGameDefinition gameDefinition = buildGameDefinition();
		gameDefinition.setDuration(duration);
		return build(queue, publisher, gameDefinition);
	}

	public static MatchRunner build(RemoteQueue queue, MatchStatePublisher publisher,
			ModelGameDefinition gameDefinition) {
		ServerMonitor monitor = new ServerMonitor(queue);

		ModelMatch match = new ModelMatch();
		match.setId(1);
		MatchRunner runner = null;
		try {
			runner = new MatchRunner(gameDefinition, match, queue, monitor, new MockMatchRunnerAPI());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (publisher == null) {
			publisher = new MatchStatePublisher("mock-test", match, queue);
		}

		runner.addListener(new ScoreUpdater());
		runner.setPublisher(publisher);
		return runner;
	}

	public static MatchRunner buildWithDefaultLuchador() {
		ModelGameDefinition gameDefinition = buildGameDefinition();
		gameDefinition.setDuration(1000);

		ModelMatch match = new ModelMatch();
		MatchRunner runner = null;
		try {
			runner = new MatchRunner(gameDefinition, match, remoteQueue, monitor, new MockMatchRunnerAPI());
		} catch (Exception e) {
			e.printStackTrace();
		}

		runner.setPublisher(new MatchStatePublisherSilent());

		runner.addListener(new OnInitAddNPC());
		runner.getOnInit().onNext(new MatchInitVO(System.currentTimeMillis()));
		runner.getOnInit().onComplete();

		return runner;
	}

	public static ModelGameDefinition buildGameDefinition() {
		ModelGameDefinition gd = new ModelGameDefinition();
		gd.setId(1);
		
		gd.setDuration(1200000);
		gd.setMinParticipants(2);
		gd.setMaxParticipants(20);
		gd.setArenaWidth(2400);
		gd.setArenaHeight(1200);
		gd.setBulletSize(16);
		gd.setLuchadorSize(60);
		gd.setFps(30);
		gd.setBuletSpeed(120);

		gd.setRadarAngle(45);
		gd.setRadarRadius(200);
		gd.setPunchAngle(90);
		gd.setLife(20);
		gd.setEnergy(30);
		gd.setPunchDamage(2);
		gd.setPunchCoolDown(2);
		gd.setMoveSpeed(50);
		gd.setTurnSpeed(180);
		gd.setTurnGunSpeed(60);
		gd.setRespawnCooldown(10);
		gd.setMaxFireCooldown(10);
		gd.setMinFireDamage(1);
		gd.setMaxFireDamage(10);
		gd.setMinFireAmount(1);
		gd.setMaxFireAmount(10);
		gd.setRestoreEnergyperSecond(3);
		gd.setRecycledLuchadorEnergyRestore(6);
		gd.setIncreaseSpeedEnergyCost(10);
		gd.setIncreaseSpeedPercentage(20);
		gd.setFireEnergyCost(2);

		gd.setRespawnX(0);
		gd.setRespawnY(0);

		gd.setGameComponents(new ArrayList<ModelGameComponent>());
		gd.setSceneComponents(new ArrayList<ModelSceneComponent>());
		gd.setCodes(new ArrayList<ModelCode>());
		gd.setSuggestedCodes(new ArrayList<ModelCode>());

		return gd;
	}

	public static Thread start(MatchRunner runner) {
		Thread t = new Thread(runner);
		t.start();
		return t;
		// runner.getOnMatchStart().blockingFirst();
	}

	public static LuchadorRunner add(MatchRunner match, ModelGameComponent component) throws Exception {
		return match.add(component).blockingFirst();
	}

}
