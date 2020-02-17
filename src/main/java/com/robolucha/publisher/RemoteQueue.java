package com.robolucha.publisher;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.robolucha.runner.Config;
import com.robolucha.runner.CriticalErrorHandler;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

public class RemoteQueue implements AutoCloseable {

	private Logger logger = Logger.getLogger(RemoteQueue.class);

	private CriticalErrorHandler criticalHandler;
	private JedisPool subscriberPool;
	private JedisPool publisherPool;
	private Gson gson;
	private Config config;

	public RemoteQueue(Config config, CriticalErrorHandler criticalHandler) {
		subscriberPool = new JedisPool(config.getRedisHost(), config.getRedisPort());
		publisherPool = new JedisPool(config.getRedisHost(), config.getRedisPort());
		gson = new Gson();
		this.criticalHandler = criticalHandler;
		this.config = config;
	}

	protected RemoteQueue() {
	}

	@Override
	public void close() {
		subscriberPool.close();
		publisherPool.close();
	}

	public Observable<Long> publishWithSuffix(String suffix, Object subjectToPublish) {
		String channel = getChannelName(subjectToPublish);
		return publish(channel + suffix, subjectToPublish);
	}

	public Observable<Long> publish(Object subjectToPublish) {
		String channel = getChannelName(subjectToPublish);
		return publish(channel, subjectToPublish);
	}

	public Observable<Long> publish(String channel, Object subjectToPublish) {
		String data = getData(subjectToPublish);
		if (publisherPool.isClosed()) {
			publisherPool = new JedisPool(config.getRedisHost(), config.getRedisPort());
		}

		Jedis publisher = publisherPool.getResource();
		Observable<Long> result = Observable.just(publisher.publish(channel, data));
		publisher.close();

		return result;
	}

	private String getData(Object subjectToPublish) {
		return this.gson.toJson(subjectToPublish);
	}

	public static String getChannelName(Class clazz) {
		return clazz.getCanonicalName();
	}

	public static String getChannelName(Object subjectToPublish) {
		return getChannelName(subjectToPublish.getClass());
	}

	public <T> BehaviorSubject<T> getSubject(Class<T> clazzToSubscribe) {
		String channel = getChannelName(clazzToSubscribe);
		return getSubject(channel, clazzToSubscribe);
	}

	public <T> BehaviorSubject<T> getSubject(String channel, Class<T> clazzToSubscribe) {

		BehaviorSubject<T> result = BehaviorSubject.create();
		logger.debug("subscribing to " + channel);

		Thread subscriber = new Thread(new Runnable() {
			Jedis subscriber;
			int retries;

			// TODO: move to config
			int maxRetries = 10000;
			int waitbetweenRetries = 200;

			private void waitForMessages() {
				if (subscriberPool.isClosed()) {
					subscriberPool = new JedisPool(config.getRedisHost(), config.getRedisPort());
				}

				subscriber = subscriberPool.getResource();
				logger.info("Building subscription to [" + channel + "]");

				JedisPubSub messageHandler = new JedisPubSub() {
					public void onMessage(String channel, String message) {
						T data = gson.fromJson(message, clazzToSubscribe);
						result.onNext(data);
					}
				};

				// this a synchronous call
				subscriber.subscribe(messageHandler, channel);
			}

			public void run() {
				synchronized (channel) {
					while (retries < maxRetries) {
						try {
							waitForMessages();
						} catch (Throwable throwable) {
							logger.error("RemoteQueue exception while waiting for message", throwable);
						}

						retries++;
						try {
							channel.wait(waitbetweenRetries);
						} catch (InterruptedException e) {
							logger.error("Interrupted while been a good boy and waiting", e);
						}

						logger.info("Retrying connection to Redis, retry: " + retries);
					}

					criticalHandler.bye("Have exhausted maximum number of retries for REDIS");
				}

				subscriber.close();
			}
		});

		result.subscribe(new ThreadKiller<>(subscriber));
		subscriber.start();

		return result;
	}

	private static class ThreadKiller<T> implements Observer<T> {
		private Logger logger = Logger.getLogger(ThreadKiller.class);

		private final Thread thread;

		ThreadKiller(Thread thread) {
			this.thread = thread;
		}

		public void onComplete() {
			thread.interrupt();
		}

		public void onSubscribe(Disposable disposable) {
		}

		public void onNext(T t) {
		}

		public void onError(Throwable throwable) {
			logger.error("RemoteQueue.ThereadKiller on error", throwable);
			thread.interrupt();
		}

	}
}
