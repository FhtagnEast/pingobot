package com.withlava.pingobot.model;

public class Status {
    private final boolean active;
    private final boolean deleted;


    public Status(boolean active, boolean deleted) {
        this.active = active;
        this.deleted = deleted;
    }

    public boolean getActive() {
        return active;
    }

    public boolean getDeleted() {
        return deleted;
    }
}
