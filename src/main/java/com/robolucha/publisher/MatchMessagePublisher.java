package com.robolucha.publisher;

import com.robolucha.game.vo.MessageVO;
import com.robolucha.runner.MatchRunner;
import com.robolucha.runner.MatchRunnerListener;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import org.apache.log4j.Logger;

public class MatchMessagePublisher implements Consumer<MessageVO>, MatchRunnerListener {

	private static Logger logger = Logger.getLogger(MatchMessagePublisher.class);

	private RemoteQueue remoteQueue;

	private Disposable disposable;

	public MatchMessagePublisher(RemoteQueue remoteQueue) {
		this.remoteQueue = remoteQueue;
	}

	public void subscribe(MatchRunner matchRunner) {
		this.disposable = matchRunner.getOnMessage().subscribe(this, new ErrorHandler());
	}

	@Override
	public void accept(MessageVO messageVO) throws Exception {
		String channel = String.format("match.%s.message", messageVO.luchadorID);
		remoteQueue.publish(channel, messageVO);
	}

	@Override
	public void dispose() {
		this.disposable.dispose();
	}

	@Override
	public boolean isDisposed() {
		return this.disposable.isDisposed();
	}

	protected class ErrorHandler implements Consumer<Throwable> {
		@Override
		public void accept(Throwable error) {
			logger.error("Error from onMessage", error);
		}
	}

}
