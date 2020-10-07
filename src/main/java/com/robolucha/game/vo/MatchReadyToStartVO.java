package com.robolucha.game.vo;

import java.util.Arrays;

public class MatchReadyToStartVO {
	public boolean ready;
	public long matchID;

	public int minParticipants;
	public int maxParticipants;
	public int participants;
	public MatchReadyToStartTeamInformationVO[] teamParticipants;
	
	public String toString() {
		return "MatchReadyToStartVO [ready=" + ready + ", matchID=" + matchID + ", minParticipants=" + minParticipants
				+ ", maxParticipants=" + maxParticipants + ", participants=" + participants + ", teamParticipants="
				+ Arrays.toString(teamParticipants) + "]";
	}

}
