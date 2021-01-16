package com.robolucha.listener;

import org.apache.log4j.Logger;

import com.robolucha.runner.Server;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.swagger.client.model.ModelMatch;

public class StartMatchListener implements Consumer<ModelMatch>, Disposable {

	static Logger logger = Logger.getLogger(StartMatchListener.class);
	private Disposable disposable;
	private Server server;

	public StartMatchListener(Server server) {
		this.server = server;
	}

	public static void listen(Server server) {
		StartMatchListener listener = new StartMatchListener(server);

		String channel = "start.match";
		logger.info("Listening to channel: " + channel);

		listener.disposable = server.getQueue().getSubject(channel, ModelMatch.class).subscribe(listener, new ErrorHandler());
	}

	protected static class ErrorHandler implements Consumer<Throwable> {
		@Override
		public void accept(Throwable error) {
			logger.error("Error from StartMatchListener", error);
		}
	}

	@Override
	public void accept(ModelMatch startMatch) throws Exception {
		logger.info("Luchador wants to start a match " + startMatch);
		server.start(startMatch);
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
