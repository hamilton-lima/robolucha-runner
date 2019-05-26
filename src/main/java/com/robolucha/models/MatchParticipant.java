package com.robolucha.models;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.model.MainGameComponent;

public class MatchParticipant {
	private long id;
	private Match matchRun;
	private MainGameComponent luchador;
	private List<CodeHistory> codePackages = new ArrayList<CodeHistory>();
	private long timeStart;

	@Override
	public String toString() {
		return "MatchParticipant [id=" + id + ", matchRun=" + matchRun + ", luchador=" + luchador + ", codePackages="
				+ codePackages + ", timeStart=" + timeStart + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Match getMatchRun() {
		return matchRun;
	}

	public void setMatchRun(Match matchRun) {
		this.matchRun = matchRun;
	}

	public MainGameComponent getLuchador() {
		return luchador;
	}

	public void setLuchador(MainGameComponent component) {
		this.luchador = component;
	}

	public List<CodeHistory> getCodePackages() {
		return codePackages;
	}

	public void setCodePackages(List<CodeHistory> codePackages) {
		this.codePackages = codePackages;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

}
