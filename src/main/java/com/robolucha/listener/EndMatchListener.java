package com.robolucha.listener;

import org.apache.log4j.Logger;

import com.robolucha.runner.Server;
import com.robolucha.shared.JSONFormat;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.swagger.client.model.ModelMatch;

public class EndMatchListener implements Consumer<ModelMatch>, Disposable {

	static Logger logger = Logger.getLogger(EndMatchListener.class);
	private Disposable disposable;
	private Server server;

	public EndMatchListener(Server server) {
		this.server = server;
	}

	public static void listen(Server server) {
		EndMatchListener listener = new EndMatchListener(server);

		String channel = "end.match";
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
	public void accept(ModelMatch endMatch) throws Exception {
		logger.info("Luchador wants to end a match " + JSONFormat.clean(endMatch.toString()));
		server.end(endMatch);
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
