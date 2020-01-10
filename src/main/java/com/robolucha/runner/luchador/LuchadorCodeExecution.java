package com.robolucha.runner.luchador;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LuchadorCodeExecution {

	private String codeName;
	private Map<String, LuchadorCommandQueue> commands;

	public LuchadorCodeExecution(String codeName) {
		this.codeName = codeName;
		this.commands = Collections.synchronizedMap(new LinkedHashMap<String, LuchadorCommandQueue>());
	}

	public String getCodeName() {
		return codeName;
	}

	public Map<String, LuchadorCommandQueue> getCommands() {
		return commands;
	}

	public void clear(String commandName) {
		commands.remove(commandName);
	}

	public void add(LuchadorCommand command) {
		LuchadorCommandQueue queue = commands.get(command.getCommand());
		if (queue == null) {
			queue = new LuchadorCommandQueue(command.getCommand());
			commands.put(command.getCommand(), queue);
		}

		queue.add(command);
	}

}
