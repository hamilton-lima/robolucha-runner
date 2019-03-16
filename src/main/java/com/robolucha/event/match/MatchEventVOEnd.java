package com.robolucha.event.match;

public class MatchEventVOEnd extends MatchEventVO {
    public static final String NAME = "MATCH-END";

    public MatchEventVOEnd() {
        super(NAME, System.currentTimeMillis());
    }
}
