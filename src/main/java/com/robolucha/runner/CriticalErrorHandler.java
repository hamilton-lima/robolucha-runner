package com.robolucha.runner;

import org.apache.log4j.Logger;

public class CriticalErrorHandler {
	static Logger logger = Logger.getLogger(CriticalErrorHandler.class);

	public void bye(String reallyCriticalReason) {
		logger.error("Something really bad happened: " + reallyCriticalReason);
		System.exit(42);
	}

}
