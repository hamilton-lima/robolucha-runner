package com.robolucha.publisher;

public class MessageEnvelope {
	public static final String EVENT = "event";
	public static final String MATCH_STATE = "match-state";
	public static final String MESSAGE = "message";
	public static final String MATCH_CREATED = "match-created";

	public String sender;
	public String type;
	public Object message;

	private MessageEnvelope(String type, Object message) {
		this.sender = "";
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

	public static MessageEnvelope buildMatchCreated(Object matchCreatedState) {
		return new MessageEnvelope(MessageEnvelope.MATCH_CREATED, matchCreatedState);
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
}
