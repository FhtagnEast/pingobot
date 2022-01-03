package com.withlava.pingobot.model;

public class LifecycleInfo {
    private final long created;
    private final long markedOnDeletion;

    public LifecycleInfo(long created, long markedOnDeletion) {
        this.created = created;
        this.markedOnDeletion = markedOnDeletion;
    }

    public long getCreated() {
        return created;
    }

    public long getMarkedOnDeletion() {
        return markedOnDeletion;
    }
}
