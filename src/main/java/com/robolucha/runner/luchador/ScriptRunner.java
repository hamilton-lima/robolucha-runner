package com.robolucha.runner.luchador;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.robolucha.runner.luchador.lua.ScriptFacade;

public class ScriptRunner implements Runnable {

	private static Logger logger = Logger.getLogger(ScriptRunner.class);

	private Object[] parameter;
	private String codeName;
	private String currentName;
	private LuchadorRunner runner;
	private boolean finished;

	ScriptRunner(LuchadorRunner runner, String codeName, Object... parameter) {
		this.codeName = codeName;
		this.parameter = parameter;
		this.runner = runner;
		this.finished = false;
	}

	//TODO: Add observable to track time and end of the execution

	@Override
	public void run() {
		Thread.currentThread().setName("ScriptRunner-Thread-GameComponentID-" + runner.getGameComponent().getId() + "-"+ codeName);

		if (logger.isDebugEnabled()) {
			logger.debug("START run(" + codeName + "," + parameter + ")");
		}

		Object result = null;

		long elapsed = 0L;
		long start = 0L;

		try {
			start = System.currentTimeMillis();
			currentName = codeName;
			if (logger.isDebugEnabled()) {
				logger.debug("== running code " + codeName + "()");
			}

			ScriptFacade facade = runner.getScriptDefinition().buildFacade(runner, codeName);
			runner.getScriptDefinition().run(facade, codeName, parameter);
			
		} catch (Exception e) {
			String message = "server.exception.error.running=run(" + codeName + "," + parameter + ") " + e.getMessage();
			if (logger.isDebugEnabled()) {
				logger.error(message, e);
			} else {
				logger.error(message);
			}

			runner.exceptionCounter++;
			runner.saveExceptionToCode(codeName, e.getMessage());

		} finally {
			try {
				runner.getScriptDefinition().afterCompile();
			} catch (Exception e) {
				if (logger.isEnabledFor(Level.WARN)) {
					logger.warn("error aftercompile running : run(" + codeName + "," + parameter + ")", e);
				}
			}
		}

		logger.debug("END run(), result=" + result);

		elapsed = System.currentTimeMillis() - start;
		currentName = null;

		this.codeName = null;
		this.parameter = null;
		this.runner = null;
		finished = true;
	}

	public String getCurrentName() {
		return currentName;
	}
	
	public boolean isFinished() {
		return finished;
	}

}
