package com.robolucha.game.vo;

import com.robolucha.models.LuchadorPublicState;

public class LuchadorPublicStateVO {

	public LuchadorPublicStateVO(LuchadorPublicState state, String name, Long ownerId) {
		this.state = state;
		this.name = name;
		this.ownerId = ownerId;
	}

	public LuchadorPublicState state;
	public String name;
	public Long ownerId;
}
