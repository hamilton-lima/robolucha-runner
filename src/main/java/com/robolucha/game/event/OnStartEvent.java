package com.robolucha.game.event;

import com.robolucha.models.LuchadorPublicState;
import com.robolucha.runner.luchador.MethodNames;

public class OnStartEvent extends LuchadorEvent {

	public OnStartEvent(LuchadorPublicState source) {
		super(source);
	}

	public String getKey() {
		return MethodNames.START;
	}

	public String getJavascriptMethod() {
		return MethodNames.START;
	}

	public Object[] getMethodParameters() {
		Object[] result = new Object[0];
		return result;
	}

}