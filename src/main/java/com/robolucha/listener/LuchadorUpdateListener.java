package com.robolucha.listener;

import org.apache.log4j.Logger;

import com.robolucha.models.Luchador;
import com.robolucha.publisher.RemoteQueue;
import com.robolucha.runner.MatchRunnerAPI;
import com.robolucha.runner.luchador.LuchadorRunner;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.swagger.client.model.MainLuchador;

public class LuchadorUpdateListener implements Consumer<MainLuchador>, Disposable {

	static Logger logger = Logger.getLogger(LuchadorUpdateListener.class);
	private Disposable disposable;

	private LuchadorRunner runner;

	@SuppressWarnings("unchecked")
	public static void listen(RemoteQueue publisher, LuchadorRunner runner) {
		LuchadorUpdateListener listener = new LuchadorUpdateListener();
		listener.runner = runner;
		runner.setUpdateListener(listener);

		String channel = String.format("luchador.%s.update", runner.getGameComponent().getId());
		logger.debug("listen " + channel);

		listener.disposable = publisher.subscribe(channel, MainLuchador.class).subscribe(listener, new ErrorHandler());
	}

	protected static class ErrorHandler implements Consumer<Throwable> {
		@Override
		public void accept(Throwable error) {
			logger.error("Error from JoinMatchListener", error);
		}
	}

	@Override
	public void accept(MainLuchador luchadorFromAPI) throws Exception {
		logger.info("Luchador updated " + luchadorFromAPI);
		Luchador luchador = MatchRunnerAPI.getInstance().mapLuchadorAPI2Bean(luchadorFromAPI);
		runner.update(luchador);
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
