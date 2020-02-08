package com.robolucha.publisher;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class MockRemoteQueue extends RemoteQueue {
    public Object lastPublished;

    public MockRemoteQueue() {
    }

    @Override
    public Observable<Long> publish(String channel, Object subjectToPublish) {
        lastPublished = subjectToPublish;
        return Observable.just(0L);
    }
    
    public <T> BehaviorSubject getSubject(String channel, Class<T> clazzToSubscribe) {
        return BehaviorSubject.create();
    }
}
