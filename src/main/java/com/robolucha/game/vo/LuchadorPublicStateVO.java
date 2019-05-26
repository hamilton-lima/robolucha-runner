package com.robolucha.game.vo;

import com.robolucha.models.LuchadorPublicState;

public class LuchadorPublicStateVO {

	public LuchadorPublicStateVO(LuchadorPublicState state, String name) {
		this.state = state;
		this.name = name;
	}

	public LuchadorPublicState state;
	public String name;
}
