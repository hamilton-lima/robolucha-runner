package com.robolucha.listener;

import org.apache.log4j.Logger;

import com.robolucha.publisher.RemoteQueue;
import com.robolucha.runner.luchador.LuchadorRunner;
import com.robolucha.shared.JSONFormat;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.swagger.client.model.ModelGameComponent;

public class LuchadorUpdateListener implements Consumer<ModelGameComponent>, Disposable {

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

		listener.disposable = publisher.subscribe(channel, ModelGameComponent.class).subscribe(listener, new ErrorHandler());
	}

	protected static class ErrorHandler implements Consumer<Throwable> {
		@Override
		public void accept(Throwable error) {
			logger.error("Error from JoinMatchListener", error);
		}
	}

	@Override
	public void accept(ModelGameComponent component) throws Exception {
		logger.info("Luchador updated " + JSONFormat.clean(component.toString()));
		runner.update(component);
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
