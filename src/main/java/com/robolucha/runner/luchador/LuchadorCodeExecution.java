package com.robolucha.runner.luchador;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LuchadorCodeExecution {

	private String codeName;
	private LinkedHashMap<String, LuchadorCommandQueue> commands;
	private Queue<LuchadorCommand> waiting2process;

	public LuchadorCodeExecution(String codeName) {
		this.codeName = codeName;
		commands = new LinkedHashMap<String, LuchadorCommandQueue>();
		waiting2process = new LinkedList<LuchadorCommand>();
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
		waiting2process.add(command);
	}

	public void moveQueue2Process() {
		while (waiting2process.size() > 0) {
			LuchadorCommand command = waiting2process.poll();
			if( command != null ) {
				LuchadorCommandQueue queue = commands.get(command.getCommand());
				if (queue == null) {
					queue = new LuchadorCommandQueue(command.getCommand());
					commands.put(command.getCommand(), queue);
				}

				queue.add(command);
			}
		}
	}

	public boolean isEmpty() {
		return getCommands().size() == 0 && waiting2process.size() == 0;
	}

}
