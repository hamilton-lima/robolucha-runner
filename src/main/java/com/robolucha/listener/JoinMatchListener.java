package com.robolucha.listener;

import org.apache.log4j.Logger;

import com.robolucha.publisher.RemoteQueue;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.MatchRunnerAPI;
import com.robolucha.shared.JSONFormat;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.swagger.client.model.MainGameComponent;
import io.swagger.client.model.MainGameDefinition;
import io.swagger.client.model.MainJoinMatch;

public class JoinMatchListener implements Consumer<MainJoinMatch>, Disposable {

	static Logger logger = Logger.getLogger(JoinMatchListener.class);
	private Disposable disposable;

	@SuppressWarnings("unused")
	private MatchRunner runner;
	private MainGameDefinition gameDefinition;

	public JoinMatchListener(MainGameDefinition gameDefinition) {
		this.gameDefinition = gameDefinition;
	}

	@SuppressWarnings("unchecked")
	public static void listen(RemoteQueue publisher, MatchRunner runner) {
		JoinMatchListener listener = new JoinMatchListener(runner.getGameDefinition());
		listener.runner = runner;
		runner.setJoinListener(listener);

		String channel = String.format("match.%s.join", runner.getMatch().getId());
		logger.debug("listen " + channel);

		listener.disposable = publisher.subscribe(channel, MainJoinMatch.class).subscribe(listener, new ErrorHandler());
	}

	protected static class ErrorHandler implements Consumer<Throwable> {
		@Override
		public void accept(Throwable error) {
			logger.error("Error from JoinMatchListener", error);
		}
	}

	@Override
	public void accept(MainJoinMatch joinMatch) throws Exception {
		logger.info("Luchador wants to join a match " + JSONFormat.clean(joinMatch.toString()));
		MainGameComponent luchador = MatchRunnerAPI.getInstance().findLuchadorById(joinMatch.getLuchadorID(),
				gameDefinition.getId());

		logger.info(">>>>>>>> Luchador found by ID " + JSONFormat.clean(luchador.toString()));
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
