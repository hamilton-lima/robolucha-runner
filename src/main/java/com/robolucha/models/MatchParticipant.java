package com.robolucha.models;

import io.swagger.client.model.ModelGameComponent;
import io.swagger.client.model.ModelMatch;

public class MatchParticipant {
	private long id;
	private ModelMatch matchRun;
	private ModelGameComponent luchador;
	private long timeStart;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ModelMatch getMatchRun() {
		return matchRun;
	}

	public void setMatchRun(ModelMatch matchRun) {
		this.matchRun = matchRun;
	}

	public ModelGameComponent getLuchador() {
		return luchador;
	}

	public void setLuchador(ModelGameComponent component) {
		this.luchador = component;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

}
