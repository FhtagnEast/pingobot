package com.withlava.pingobot.database.model;

import java.util.Optional;

public class Notification {
    private final long id;

    private final long userId;
    private final long chatId;
    private final Optional<Long> updateCollectorMessageId;
    private final Optional<Long> editMessageId;

    private final String description;

    private final Status status;
    private final DelayInfo delayInfo;
    private final ExecutionInfo executionInfo;
    private final LifecycleInfo lifecycleInfo;

    public Notification(
            long id,
            long userId,
            long chatId,
            Optional<Long> updateCollectorMessageId,
            Optional<Long> editMessageId,
            String description,
            Status status,
            DelayInfo delayInfo,
            ExecutionInfo executionInfo,
            LifecycleInfo lifecycleInfo)
    {
        this.id = id;
        this.userId = userId;
        this.chatId = chatId;
        this.updateCollectorMessageId = updateCollectorMessageId;
        this.editMessageId = editMessageId;
        this.description = description;
        this.status = status;
        this.delayInfo = delayInfo;
        this.executionInfo = executionInfo;
        this.lifecycleInfo = lifecycleInfo;
    }

    public Notification withCollectorMessageId(long messageId) {
        return new Notification(
                id,
                userId,
                chatId,
                Optional.of(messageId),
                editMessageId,
                description,
                status,
                delayInfo,
                executionInfo,
                lifecycleInfo
        );
    }

    public Notification completed() {
        return new Notification(
                id,
                userId,
                chatId,
                updateCollectorMessageId,
                editMessageId,
                description,
                status,
                delayInfo,
                new ExecutionInfo(
                        executionInfo.getNextExecutionTime() + delayInfo.getOnCompletedDelay(),
                        Optional.of(executionInfo.getNextExecutionTime())),
                lifecycleInfo
        );
    }

    public Notification uncompleted() {
        return new Notification(
                id,
                userId,
                chatId,
                updateCollectorMessageId,
                editMessageId,
                description,
                status,
                delayInfo,
                new ExecutionInfo(
                        executionInfo.getNextExecutionTime() + delayInfo.getOnUncompletedDelay(),
                        Optional.of(executionInfo.getNextExecutionTime())),
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

    public Optional<Long> getUpdateCollectorMessageId() {
        return updateCollectorMessageId;
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
