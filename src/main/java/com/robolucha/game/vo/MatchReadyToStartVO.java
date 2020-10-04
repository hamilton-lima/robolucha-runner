package com.robolucha.game.vo;

public class MatchReadyToStartVO {
	public boolean ready;
	public long matchID;

	public int minParticipants;
	public int maxParticipants;
	public int participants;
	public MatchReadyToStartTeamInformationVO[] teamParticipants;
}
