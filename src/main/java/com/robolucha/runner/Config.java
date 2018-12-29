package com.robolucha.runner;

public class Config {

	public static final String REDIS_HOST = "REDIS_HOST";
	public static final String REDIS_PORT = "REDIS_PORT";
	public static final String API_CLIENT_BASEPATH = "API_CLIENT_BASEPATH";

	public static final String DEFAULT_REDIS_HOST = "localhost";
	public static final int DEFAULT_REDIS_PORT = 6379;
	public static final String DEFAULT_API_CLIENT_BASEPATH = "http://api.robolucha.com:8080";

	private String redisHost;
	private int redisPort;
	private String basePath;

	private static Config instance;

	private Config() {
		setRedisHost();
		setRedisPort();
		setAPIClientBasePath();
	}

	private void setRedisHost() {
		String redisHost = System.getenv(REDIS_HOST);
		if (isValidString(redisHost)) {
			this.redisHost = redisHost;
		} else {
			this.redisHost = DEFAULT_REDIS_HOST;
		}
	}

	private void setRedisPort() {
		try {
			this.redisPort = Integer.parseInt(System.getenv(REDIS_PORT));
		} catch (NumberFormatException e) {
			this.redisPort = DEFAULT_REDIS_PORT;
		}
	}

	private void setAPIClientBasePath() {
		String basePath = System.getenv(API_CLIENT_BASEPATH);
		if (isValidString(basePath)) {
			this.basePath = basePath;
		} else {
			this.basePath = DEFAULT_API_CLIENT_BASEPATH;
		}

	}

	boolean isValidString(String s) {
		return s != null && !s.trim().isEmpty();
	}

	static void reset() {
		instance = null;
	}

	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
		}
		return instance;
	}

	public String getRedisHost() {
		return redisHost;
	}

	public int getRedisPort() {
		return redisPort;
	}

	public String getBasePath() {
		return basePath;
	}

}
