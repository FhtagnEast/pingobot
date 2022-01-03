package com.withlava.pingobot.database.model;

public class Status {
    private final boolean active;
    private final boolean deleted;


    public Status(boolean active, boolean deleted) {
        this.active = active;
        this.deleted = deleted;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
