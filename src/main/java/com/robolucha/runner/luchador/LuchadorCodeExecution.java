package com.robolucha.runner.luchador;

import java.util.Iterator;
import java.util.LinkedList;

public class LuchadorCodeExecution {

	private String codeName;
	private long start;
	private LinkedList<LuchadorCommand> commands;

	public LuchadorCodeExecution(String codeName, long start) {
		this.codeName = codeName;
		this.commands = new LinkedList<LuchadorCommand>();
		this.start = start;
	}

	public String getCodeName() {
		return codeName;
	}

	public LinkedList<LuchadorCommand> getCommands() {
		return commands;
	}

	public long getStart() {
		return start;
	}

	public void clear(String prefix) {
		Iterator<LuchadorCommand> iterator = commands.iterator();
		while (iterator.hasNext()) {
			LuchadorCommand command = iterator.next();

			if (command.getKey().startsWith(prefix)) {
				iterator.remove();
			}

		}
	}

	@Override
	public String toString() {
		return "LuchadorCodeExecution [codeName=" + codeName + ", start=" + start + ", commands=" + commands + "]";
	}

}
