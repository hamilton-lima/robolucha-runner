package com.robolucha.models;

import io.swagger.client.model.MainGameComponent;
import io.swagger.client.model.MainMatch;

public class MatchScore {

	private long id;
	private MainMatch matchRun;
	private MainGameComponent gameComponent;
	private int kills;
	private int deaths;
	private int score;

	@Override
	public String toString() {
		Integer luchadorId = 0;
		Integer matchRunId = 0;

		if (gameComponent != null) {
			luchadorId = gameComponent.getId();
		}
		if (matchRun != null) {
			matchRunId = matchRun.getId();
		}

		return "MatchRunScore [id=" + id + ", matchRun=" + matchRunId + ", luchador=" + luchadorId + ", kills=" + kills
				+ ", deaths=" + deaths + ", score=" + score + "]";
	}

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

	public MainGameComponent getGameComponent() {
		return gameComponent;
	}

	public void setGameComponent(MainGameComponent gameComponent) {
		this.gameComponent = gameComponent;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
