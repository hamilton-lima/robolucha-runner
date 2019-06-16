package com.robolucha.runner.code;

import java.util.Arrays;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.robolucha.runner.luchador.LuchadorRunner;

public class ScriptRunner implements Runnable {

	private static Logger logger = Logger.getLogger(ScriptRunner.class);

	private Object[] parameter;
	private String codeName;
	private LuchadorRunner runner;
	private boolean finished;

	public ScriptRunner(LuchadorRunner runner, String codeName, Object... parameter) {
		this.codeName = codeName;
		this.parameter = parameter;
		this.runner = runner;
		this.finished = false;
	}

	// TODO: Add observable to track time and end of the execution

	@Override
	public void run() {
		Thread.currentThread()
				.setName("ScriptRunner.Thread.GameComponentID." + runner.getGameComponent().getId() + "." + codeName);

		if (logger.isDebugEnabled()) {
			logger.debug("START run(" + codeName + "," + parameter + ")");
		}

		Object result = null;

		long elapsed = 0L;
		long start = 0L;

		try {
			start = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug("== running code " + codeName + "()");
			}

			LuchadorScriptFacade facade = runner.getScriptDefinition().buildFacade(runner, codeName);
			runner.getScriptDefinition().run(facade, codeName, parameter);

		} catch (Exception e) {
			String message = "server.exception.error.running=run(" + codeName + "," + parameter + ") " + e.getMessage();
			if (logger.isDebugEnabled()) {
				logger.error(message, e);
			} else {
				logger.error(message);
			}

			runner.setExceptionCounter(runner.getExceptionCounter() + 1);
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
		finished = true;
	}

	public boolean isFinished() {
		return finished;
	}

	@Override
	public String toString() {
		String luchadorName = null;
		if (runner != null && runner.getGameComponent() != null) {
			luchadorName = runner.getGameComponent().getName();
		}

		return "ScriptRunner [codeName=" + codeName + ", luchadorName=" + luchadorName + ", finished=" + finished
				+ ", parameter=" + Arrays.toString(parameter) + "]";
	}

}
