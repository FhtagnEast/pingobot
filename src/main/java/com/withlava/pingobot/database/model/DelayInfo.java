package com.withlava.pingobot.database.model;

public class DelayInfo {
    //TODO: you can store cron schedule here
    private final long onCompletedDelay; //add ms to name
    private final long onUncompletedDelay;


    public DelayInfo(long onCompletedDelay, long onUncompletedDelay) {
        this.onCompletedDelay = onCompletedDelay;
        this.onUncompletedDelay = onUncompletedDelay;
    }

    public long getOnCompletedDelay() {
        return onCompletedDelay;
    }

    public long getOnUncompletedDelay() {
        return onUncompletedDelay;
    }
}
