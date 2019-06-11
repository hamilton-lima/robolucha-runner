package com.robolucha.models;

import io.swagger.client.model.MainGameComponent;
import io.swagger.client.model.MainMatch;

public class MatchParticipant {
	private long id;
	private MainMatch matchRun;
	private MainGameComponent luchador;
	private long timeStart;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MainMatch getMatchRun() {
		return matchRun;
	}

	public void setMatchRun(MainMatch matchRun) {
		this.matchRun = matchRun;
	}

	public MainGameComponent getLuchador() {
		return luchador;
	}

	public void setLuchador(MainGameComponent component) {
		this.luchador = component;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

}
