package com.robolucha.listener;

import org.apache.log4j.Logger;

import com.robolucha.runner.Server;
import com.robolucha.shared.JSONFormat;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.swagger.client.model.ModelJoinMatch;

public class JoinMatchListener implements Consumer<ModelJoinMatch>, Disposable {

	static Logger logger = Logger.getLogger(JoinMatchListener.class);
	private Disposable disposable;
	private Server server;

	public JoinMatchListener(Server server) {
		this.server = server;
	}

	public static void listen(Server server) {
		JoinMatchListener listener = new JoinMatchListener(server);

		String channel = "join.match";
		logger.debug("listen " + channel);

		listener.disposable = server.getQueue().getSubject(channel, ModelJoinMatch.class).subscribe(listener,
				new ErrorHandler());
	}

	protected static class ErrorHandler implements Consumer<Throwable> {
		@Override
		public void accept(Throwable error) {
			logger.error("Error from JoinMatchListener", error);
		}
	}

	@Override
	public void accept(ModelJoinMatch joinMatch) throws Exception {
		logger.info("Luchador wants to join a match " + JSONFormat.clean(joinMatch.toString()));
		server.add2JoinMatchQueue(joinMatch);
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
