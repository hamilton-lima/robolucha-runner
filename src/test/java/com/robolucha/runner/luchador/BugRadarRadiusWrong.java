package com.robolucha.runner.luchador;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.robolucha.event.match.MatchEventVO;
import com.robolucha.game.event.LuchadorEvent;
import com.robolucha.game.event.LuchadorEventListener;
import com.robolucha.game.event.MatchEventListener;
import com.robolucha.game.event.OnFoundEvent;
import com.robolucha.game.vo.LuchadorPublicStateVO;
import com.robolucha.game.vo.MatchRunStateVO;
import com.robolucha.models.Luchador;
import com.robolucha.models.LuchadorMatchState;
import com.robolucha.models.LuchadorPublicState;
import com.robolucha.publisher.MessageEnvelope;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.runner.MatchRunner;
import com.robolucha.test.MockLuchador;
import com.robolucha.test.MockMatchRunner;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;



public class BugRadarRadiusWrong {
	
	private static Logger logger = Logger.getLogger(BugRadarRadiusWrong.class);
	
	LuchadorPublicState finalState = null;
	protected int defaultLife;
	protected int bodyCount;
	public Object lastPublished;
	LuchadorPublicStateVO found = null;
	public int notFoundCounter;
	int foundCount = 0;

	private class RemoteQueueInspector extends RemoteQueue {
		
		public RemoteQueueInspector() {
		}

		@Override
		public Observable<Long> publish(String channel, Object subjectToPublish) {
			lastPublished = subjectToPublish;
			MatchRunStateVO publishedState = (MatchRunStateVO) ((MessageEnvelope) lastPublished).message; 
			logger.debug(">> Publishing data: luchadores.size=" + publishedState.luchadores.size());

			found = null;
			
			publishedState.luchadores.forEach((luchador) -> {
				if (luchador.state.id == 2L) {
					found = luchador;
				}
			});

			if (found == null) {
				notFoundCounter++;
			}

			return Observable.just(0L);
		}

		public <T> BehaviorSubject subscribe(String channel, Class<T> clazzToSubscribe) {
			return BehaviorSubject.create();
		}
	}

	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRun() throws Exception {
		logger.setLevel(Level.DEBUG);
		RemoteQueueInspector queue = new RemoteQueueInspector();
		MatchRunner match = MockMatchRunner.build(5000, queue);
		match.getGameDefinition().setMinParticipants(2);
		Luchador a = MockLuchador.build(1L, MethodNames.ON_REPEAT, "--turnGun(90)");
		Luchador b = MockLuchador.build(-2L, MethodNames.ON_REPEAT, "--turnGun(90)");

		match.add(a);
		match.add(b);

		// set initial position for luchador
		match.getOnMatchStart().subscribe(new Consumer<MatchEventVO>() {
			public void accept(MatchEventVO arg0) throws Exception {

				LuchadorRunner runnerA = match.getRunners().get(1L);
				LuchadorRunner runnerB = match.getRunners().get(-2L);
				runnerA.cleanUpStateAtTheEnd = false;
				runnerB.cleanUpStateAtTheEnd = false;

				defaultLife = runnerB.getGameComponent().getLife();

				runnerA.getState().setGunAngle(0);
				runnerA.getState().setX(100);
				runnerA.getState().setY(100);
				
				runnerB.getState().setGunAngle(180);
				runnerB.getState().setX(299);
				runnerB.getState().setY(100);

				logger.debug(">> luchador A: " + runnerA.getState().getPublicState());
				logger.debug(">> luchador B: " + runnerB.getState().getPublicState());
			}

		});
		
		match.addListener(new LuchadorEventListener(){
			public void listen(LuchadorEvent event){
				logger.info(">>> event : " + event) ;
				if (event instanceof OnFoundEvent){
					logger.debug("FOUND OTHER! " + event);
					foundCount++;
				}
			}
		});
		

        MockMatchRunner.start(match);

		match.getOnMatchEnd().blockingSubscribe(new Consumer<MatchEventVO>() {
			public void accept(MatchEventVO arg0) throws Exception {
				logger.debug("END! foundCount = " + foundCount);
				LuchadorRunner runner = match.getRunners().get(-2L);
				finalState = runner.getState().getPublicState();
			}
		});
		
        logger.info("START >>>");
        
        assertTrue("onFound event was triggered " + foundCount, foundCount > 0);
        
	}
	
}
