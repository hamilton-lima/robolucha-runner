package com.robolucha.listener;

import org.apache.log4j.Logger;

import com.robolucha.models.Luchador;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.MatchRunnerAPI;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.swagger.client.model.MainJoinMatch;

public class JoinMatchListener implements Consumer<MainJoinMatch>, Disposable{

	static Logger logger = Logger.getLogger(JoinMatchListener.class);
	private Disposable disposable;
	
	@SuppressWarnings("unused")
	private MatchRunner runner;

	@SuppressWarnings("unchecked")
	public static void listen(RemoteQueue publisher, MatchRunner runner) {
		JoinMatchListener listener = new JoinMatchListener();
		listener.runner = runner;
		runner.setJoinListener(listener);

		String channel = String.format("match.%s.join", runner.getMatch().getId());
		logger.debug("listen " + channel );

		listener.disposable = publisher
				.subscribe(channel, MainJoinMatch.class)
				.subscribe(listener, new ErrorHandler());
	}

	protected static class ErrorHandler implements Consumer<Throwable> {
		@Override
		public void accept(Throwable error) {
			logger.error("Error from JoinMatchListener", error);
		}
	}

	@Override
	public void accept(MainJoinMatch joinMatch) throws Exception {
		logger.info("Luchador wants to join a match " + joinMatch);
		Luchador luchador = MatchRunnerAPI.getInstance().findLuchadorById(joinMatch.getLuchadorID().longValue());
		logger.info("Luchador found by ID " + luchador);
		runner.addLuchador(luchador);
		
	}

	@Override
	public void dispose() {
		this.disposable.dispose();
	}

	@Override
	public boolean isDisposed() {
		return this.disposable.isDisposed();
	}


}
