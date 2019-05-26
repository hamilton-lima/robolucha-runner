package com.robolucha.models;

import io.swagger.client.model.MainCode;

// TODO: remove this class
public class Code extends MainCode {

	public Code() {
		
	}
	
	public Code(String event, String script) {
		this.setEvent(event);
		this.setScript(script);
	}
}
