package com.robolucha.runner.luchador;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.robolucha.monitor.ServerMonitor;
import com.robolucha.monitor.ThreadMonitor;
import com.robolucha.publisher.MatchStatePublisher;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.runner.Config;
import com.robolucha.runner.MatchRunner;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.swagger.client.model.MainGameComponent;
import io.swagger.client.model.MainMatch;

// @deprecated need to define proper way to simulate match
public class BugNaoRetornandoMatchRunnerAtivoTest {

	private static Logger logger = Logger
			.getLogger(BugNaoRetornandoMatchRunnerAtivoTest.class);

	RemoteQueue queue = new RemoteQueue(Config.getInstance());
	MainMatch match = new MainMatch();

	MatchStatePublisher publisher = new MatchStatePublisher("test", match, queue);

	/**
	 * inicia uma partida e le do ThreadMonitor qual a partida atual, conclui a
	 * primeira e inicia a segunda recuperando novamente a partida atual do
	 * ThreadMonitor os valores precisam ser diferentes
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testRun() throws Exception {
		RemoteQueue queue = new RemoteQueue(Config.getInstance());
		ServerMonitor monitor = new ServerMonitor(queue);

		MatchRunner match = MockMatchRunner.build();
		match.getGameDefinition().setMinParticipants(1);
		match.getGameDefinition().setDuration(500);

		MainGameComponent a = MockLuchador.build();
		a.setId(1);

		match.add(a);

		while (match.getRunners().size() < 1) {
			logger.debug("esperando lutchadores se preparem para o combate (1)");
			Thread.sleep(200);
		}

//		// start the match
//		Thread t = Entrypoint.buildRunner(match, queue, ThreadMonitor.getInstance(), publisher, monitor);
//		t.start();

		MatchRunner runner = ThreadMonitor.getInstance().getMatch();
		logger.debug("*** primeira partida : " + runner.getThreadName());
		String primeira = runner.getThreadName();

		// stop the match
		Thread.sleep(500);
		match.kill();
		Thread.sleep(500);

		// depois que acabou tem que retornar nulo
		runner = ThreadMonitor.getInstance().getMatch();
		assertNull(runner);

		match = MockMatchRunner.build();
		match.getGameDefinition().setMinParticipants(1);
		match.getGameDefinition().setDuration(500);

		match.add(a);

		while (match.getRunners().size() < 1) {
			logger.debug("esperando lutchadores se preparem para o combate (2)");
			Thread.sleep(200);
		}

//		// start the match
//		t = Entrypoint.buildRunner(match, queue, ThreadMonitor.getInstance(), publisher, monitor);
//		t.start();

		runner = ThreadMonitor.getInstance().getMatch();
		logger.debug("*** segunda partida : " + runner.getThreadName());
		String segunda = runner.getThreadName();

		// stop the match
		Thread.sleep(500);
		match.kill();
		Thread.sleep(500);

		// depois que acabou tem que retornar nulo
		runner = ThreadMonitor.getInstance().getMatch();
		assertNull(runner);

		assertTrue(!primeira.equals(segunda));

	}
}
