package com.robolucha.runner.luchador;

import java.util.LinkedList;

public class LuchadorCommandQueue {

	private String command;
	private LinkedList<LuchadorCommand> commands;

	public LuchadorCommandQueue(String command) {
		this.command = command;
		this.commands = new LinkedList<LuchadorCommand>();
	}

	public String getCommand() {
		return command;
	}

	public LuchadorCommand getFirst() {
		if(commands.isEmpty()) {
			return null;
		}
		return commands.getFirst();
	}

	public void add(LuchadorCommand command) {
		commands.addLast(command);
	}

	public void remove(LuchadorCommand command) {
		commands.remove(command);
	}
}
