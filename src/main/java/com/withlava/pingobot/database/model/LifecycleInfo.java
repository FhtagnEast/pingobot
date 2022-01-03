package com.withlava.pingobot.database.model;

import java.util.Optional;

public class LifecycleInfo {
    private final long created;
    private final Optional<Long> markedOnDeletion;

    public LifecycleInfo(long created, Optional<Long> markedOnDeletion) {
        this.created = created;
        this.markedOnDeletion = markedOnDeletion;
    }

    public long getCreated() {
        return created;
    }

    public Optional<Long> getMarkedOnDeletion() {
        return markedOnDeletion;
    }
}
