package com.withlava.pingobot.model;

public class ExecutionInfo {
    private final long nextExecutionTime;
    private final long lastExecutionTime;

    public ExecutionInfo(long nextExecutionTime, long lastExecutionTime) {
        this.nextExecutionTime = nextExecutionTime;
        this.lastExecutionTime = lastExecutionTime;
    }

    public long getNextExecutionTime() {
        return nextExecutionTime;
    }

    public long getLastExecutionTime() {
        return lastExecutionTime;
    }
}
