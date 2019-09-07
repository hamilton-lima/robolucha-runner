package com.robolucha.runner.luchador;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LuchadorCommandQueue {

	private String command;
	private List<LuchadorCommand> commands;

	public LuchadorCommandQueue(String command) {
		this.command = command;
		this.commands = Collections.synchronizedList(new LinkedList<LuchadorCommand>());
	}

	public String getCommand() {
		return command;
	}

	public LuchadorCommand getFirst() {
		if(commands.isEmpty()) {
			return null;
		}
		return commands.get(0);
	}

	public void add(LuchadorCommand command) {
		commands.add(command);
	}

	public void remove(LuchadorCommand command) {
		commands.remove(command);
	}
}
