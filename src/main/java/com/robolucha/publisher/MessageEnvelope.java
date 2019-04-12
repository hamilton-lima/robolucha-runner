package com.robolucha.publisher;

public class MessageEnvelope {
	public MessageEnvelope(String type, Object message)
	{
		this.type = type;
		this.message = message;
	}
	String type;
	Object message;
}
