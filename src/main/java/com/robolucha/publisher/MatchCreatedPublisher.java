package com.robolucha.publisher;

import org.apache.log4j.Logger;

import com.robolucha.game.vo.MatchReadyToStartVO;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.MatchRunnerListener;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MatchCreatedPublisher implements Consumer<MatchReadyToStartVO>, MatchRunnerListener {

	private static Logger logger = Logger.getLogger(MatchCreatedPublisher.class);
	private RemoteQueue remoteQueue;
	private Disposable disposable;

	public MatchCreatedPublisher(RemoteQueue remoteQueue) {
		this.remoteQueue = remoteQueue;
	}

	public void subscribe(MatchRunner matchRunner) {
		this.disposable = matchRunner.getOnCheckingReadiness().subscribe(this, new ErrorHandler());
	}

	protected class ErrorHandler implements Consumer<Throwable> {
		@Override
		public void accept(Throwable error) {
			logger.error("Error from onMessage", error);
		}
	}

	@Override
	public void accept(MatchReadyToStartVO info) throws Exception {
		String channel = String.format("match.%s.state", info.matchID);
		MessageEnvelope envelope = MessageEnvelope.buildMatchCreated(info);
		remoteQueue.publish(channel, envelope);
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
