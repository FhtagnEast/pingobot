package com.withlava.pingobot.database.model;

import java.util.Optional;

//TODO: try extract this shit to kind of log
public class ExecutionInfo {
    // it depends on delay
    private final long nextExecutionTime;
    private final Optional<Long> lastExecutionTime;
    private final Optional<Long> lastCompletedTime;

    public ExecutionInfo(long nextExecutionTime, Optional<Long> lastExecutionTime, Optional<Long> lastCompletedTime) {
        this.nextExecutionTime = nextExecutionTime;
        this.lastExecutionTime = lastExecutionTime;
        this.lastCompletedTime = lastCompletedTime;
    }

    public long getNextExecutionTime() {
        return nextExecutionTime;
    }

    public Optional<Long> getLastExecutionTime() {
        return lastExecutionTime;
    }

    public Optional<Long> getLastCompletedTime() {
        return lastCompletedTime;
    }
}
