package com.withlava.pingobot.database.model;

import java.util.Optional;

public class Notification {
    private final long id;

    private final long userId;
    private final long chatId;

    private final String description;

    // the sameas filter of deletion
    private final Status status;
    private final DelayInfo delayInfo;
    private final ExecutionInfo executionInfo;
    private final LifecycleInfo lifecycleInfo;

    public Notification(
            long id,
            long userId,
            long chatId,
            String description,
            Status status,
            DelayInfo delayInfo,
            ExecutionInfo executionInfo,
            LifecycleInfo lifecycleInfo)
    {
        this.id = id;
        this.userId = userId;
        this.chatId = chatId;
        this.description = description;
        this.status = status;
        this.delayInfo = delayInfo;
        this.executionInfo = executionInfo;
        this.lifecycleInfo = lifecycleInfo;
    }

    public Notification completed() {
        return new Notification(
                id,
                userId,
                chatId,
                description,
                status,
                delayInfo,
                new ExecutionInfo(
                        executionInfo.getNextExecutionTime() + delayInfo.getOnCompletedDelay(),
                        Optional.of(executionInfo.getNextExecutionTime()),
                        Optional.of(executionInfo.getNextExecutionTime())),
                lifecycleInfo
        );
    }

    public Notification uncompleted() {
        return new Notification(
                id,
                userId,
                chatId,
                description,
                status,
                delayInfo,
                new ExecutionInfo(
                        executionInfo.getNextExecutionTime() + delayInfo.getOnUncompletedDelay(),
                        Optional.of(executionInfo.getNextExecutionTime()),
                        executionInfo.getLastCompletedTime()),
                lifecycleInfo
        );
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getChatId() {
        return chatId;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public DelayInfo getDelayInfo() {
        return delayInfo;
    }

    public ExecutionInfo getExecutionInfo() {
        return executionInfo;
    }

    public LifecycleInfo getLifecycleInfo() {
        return lifecycleInfo;
    }
}
