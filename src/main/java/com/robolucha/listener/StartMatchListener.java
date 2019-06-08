package com.robolucha.listener;

import org.apache.log4j.Logger;

import com.robolucha.publisher.RemoteQueue;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.MatchRunnerAPI;
import com.robolucha.runner.Server;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.swagger.client.model.MainGameComponent;
import io.swagger.client.model.MainGameDefinition;
import io.swagger.client.model.MainJoinMatch;
import io.swagger.client.model.MainMatch;

public class StartMatchListener implements Consumer<MainJoinMatch>, Disposable {

	static Logger logger = Logger.getLogger(StartMatchListener.class);
	private Disposable disposable;
	private Server server;

	public StartMatchListener(Server server) {
		this.server = server;
	}

	@SuppressWarnings("unchecked")
	public static void listen(Server server) {
		StartMatchListener listener = new StartMatchListener(server);

		String channel = "start.match";
		logger.debug("listen " + channel);

		listener.disposable = server.getQueue().subscribe(channel, MainJoinMatch.class).subscribe(listener, new ErrorHandler());
	}

	protected static class ErrorHandler implements Consumer<Throwable> {
		@Override
		public void accept(Throwable error) {
			logger.error("Error from StartMatchListener", error);
		}
	}

	@Override
	public void accept(MainJoinMatch joinMatch) throws Exception {
		logger.info("Luchador wants to start a match " + joinMatch);
		this.server.start(joinMatch);
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
