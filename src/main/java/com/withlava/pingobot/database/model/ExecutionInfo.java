package com.withlava.pingobot.database.model;

import java.util.Optional;

public class ExecutionInfo {
    private final long nextExecutionTime;
    private final Optional<Long> lastExecutionTime;

    public ExecutionInfo(long nextExecutionTime, Optional<Long> lastExecutionTime) {
        this.nextExecutionTime = nextExecutionTime;
        this.lastExecutionTime = lastExecutionTime;
    }

    public long getNextExecutionTime() {
        return nextExecutionTime;
    }

    public Optional<Long> getLastExecutionTime() {
        return lastExecutionTime;
    }
}
