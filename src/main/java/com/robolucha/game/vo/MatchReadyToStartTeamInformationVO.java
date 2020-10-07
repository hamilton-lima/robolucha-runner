package com.robolucha.game.vo;

public class MatchReadyToStartTeamInformationVO {
	public long teamID;
	public int minParticipants;
	public int maxParticipants;
	public int participants;

	public String toString() {
		return "MatchReadyToStartTeamInformationVO [teamID=" + teamID + ", minParticipants=" + minParticipants
				+ ", maxParticipants=" + maxParticipants + ", participants=" + participants + "]";
	}
}
