package com.robolucha.models;

import io.swagger.client.model.MainGameComponent;

public class MatchScore {

	private long id;
	private Match matchRun;
	private MainGameComponent gameComponent;
	private int kills;
	private int deaths;
	private int score;

	@Override
	public String toString() {
		Integer luchadorId = 0;
		Long matchRunId = 0L;

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

	public Match getMatchRun() {
		return matchRun;
	}

	public void setMatchRun(Match matchRun) {
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
