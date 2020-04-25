package com.robolucha.monitor;

import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.robolucha.runner.MatchRunner;

public class ThreadMonitor {

	public static final long SMALL_SLEEP = 5;
	public static final long MEDIUM_SLEEP = 60;
	private static final int TIME_BETWEEN_REPORTS = 10000;

	static Logger logger = Logger.getLogger(ThreadMonitor.class);

	private static ThreadMonitor instance;
	LinkedHashMap<String, ThreadStatus> threads;
	protected boolean reporting;
	private Gson gson;

	public ThreadMonitor() {
		threads = new LinkedHashMap<String, ThreadStatus>();
		instance = this;
		gson = new Gson();

		Thread reporter = new Thread(new Runnable() {
			public void run() {
				Thread.currentThread().setName("ThreadMonitor-Report");
				reporting = true;
				while (reporting) {
					synchronized (instance) {
						String json = gson.toJson(getStatus());
						String report = String.format("THREAD-MONITOR.report:%s", json);
						logger.info(report);

						try {
							instance.wait(TIME_BETWEEN_REPORTS);
						} catch (InterruptedException e) {
							logger.error("Interrupted while been a good boy and waiting", e);
						}
					}
				}
			}

		});

		reporter.start();
	}

	public void register(MatchRunner thread) {
		threads.put(thread.getThreadName(), thread);
	}

	public void remove(ThreadStatus thread) {
		threads.remove(thread.getThreadName());
	}

	public static ThreadMonitor getInstance() {
		if (instance == null) {
			instance = new ThreadMonitor();
		}
		return instance;
	}

	public MatchRunner getMatch() {

		Iterator<String> iterator = threads.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Object one = threads.get(key);

			if (one instanceof MatchRunner) {
				if (((MatchRunner) one).isAlive()) {
					return (MatchRunner) one;
				}
			}
		}

		return null;
	}


	public void remove(Integer matchID) {
		Object key = findThreadKeyByMatchID(matchID);
		if( key != null ) {
			threads.remove(key);
		} else {
			logger.warn("MatchID not found in threads: " + matchID);
		}
	}
	
	public MatchRunner getMatch(Integer matchID) {
		Object key = findThreadKeyByMatchID(matchID);
		if( key != null ) {
			return (MatchRunner) threads.get(key);
		}
		
		return null;
	}

	public Object findThreadKeyByMatchID(Integer matchID) {
		Iterator<String> iterator = threads.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Object one = threads.get(key);

			if (one instanceof MatchRunner) {
				MatchRunner runner = (MatchRunner) one;
				if (runner.isAlive() && runner.getMatch().getId().equals(matchID)) {
					return key;
				}
			}
		}

		return null;
	}

	public ThreadStatusVO[] getStatus() {

		ThreadStatusVO[] result = new ThreadStatusVO[threads.size()];
		ThreadStatus one;
		int pos = 0;

		Iterator<String> iterator = threads.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			one = threads.get(key);
			result[pos] = new ThreadStatusVO(one.getThreadName(), one.getThreadStatus(), one.getThreadStartTime());
			pos++;
		}

		return result;
	}

	private static long counter = 0;

	public static String getUID() {
		counter++;
		return Long.toString(counter);
	}

	// TODO add to Threadstatus and control ack of errors
	public void addException(String threadName, MessageList messageList) {
		logger.error("addException, thread=" + threadName + " errors=" + messageList);
	}

	public void addException(String threadName, String message) {
		logger.error("addException, thread=" + threadName + " errors=" + message);
	}

	public void contextDestroyed() {
		logger.debug("--- APP is exiting, time to shutdown all the threads.");

		// stop reporting Thread status
		reporting = false;

		Iterator<String> iterator = threads.keySet().iterator();
		while (iterator.hasNext()) {

			String key = iterator.next();
			ThreadStatus one = threads.get(key);

			try {
				if (one != null) {
					one.kill();
				}
			} catch (Exception e) {
				logger.error("--- THREAD : " + one.getThreadName() + " is a highlander...", e);
			}

		}

	}

	public void alive(String threadName) {
		ThreadStatus thread = threads.get(threadName);
		if (thread != null) {
			thread.setLastAlive(System.currentTimeMillis());
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("tentativa de atualizar ultimo alive de thread nao encontrado : " + threadName);
			}
		}
	}

}
