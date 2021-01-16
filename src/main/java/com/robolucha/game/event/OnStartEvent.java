package com.robolucha.game.event;

import com.robolucha.models.LuchadorPublicState;

public class OnStartEvent extends LuchadorEvent {
	public static final String ON_START = "onStart";

	public OnStartEvent(LuchadorPublicState source) {
		super(source);
	}

	public String getKey() {
		return ON_START;
	}

	public String getJavascriptMethod() {
		return ON_START;
	}

	public Object[] getMethodParameters() {
		Object[] result = new Object[0];
		return result;
	}

}
