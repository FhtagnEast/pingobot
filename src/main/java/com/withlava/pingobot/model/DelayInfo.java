package com.withlava.pingobot.model;

public class DelayInfo {
    private final long onCompletedDelay;
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
