package com.robolucha.runner.luchador;

import java.util.LinkedHashMap;

public class LuchadorCodeExecution {

	private String codeName;
	private long start;
	private LinkedHashMap<String, LuchadorCommandQueue> commands;

	public LuchadorCodeExecution(String codeName, long start) {
		this.codeName = codeName;
		this.commands = new LinkedHashMap<String, LuchadorCommandQueue>();
		this.start = start;
	}

	public String getCodeName() {
		return codeName;
	}

	public LinkedHashMap<String, LuchadorCommandQueue> getCommands() {
		return commands;
	}

	public long getStart() {
		return start;
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
