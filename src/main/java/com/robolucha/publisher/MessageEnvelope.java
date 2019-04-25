package com.robolucha.publisher;

public class MessageEnvelope {
	public static final String EVENT = "event";
	public static final String MATCH_STATE = "match-state";
	public static final String MESSAGE = "message";

	public String type;
	public Object message;

	private MessageEnvelope(String type, Object message) {
		this.type = type;
		this.message = message;
	}

	public static MessageEnvelope buildEvent(Object event) {
		return new MessageEnvelope(MessageEnvelope.EVENT, event);
	}

	public static MessageEnvelope buildMatchState(Object matchState) {
		return new MessageEnvelope(MessageEnvelope.MATCH_STATE, matchState);
	}

	public static MessageEnvelope buildMessage(Object message) {
		return new MessageEnvelope(MessageEnvelope.MESSAGE, message);
	}
}
