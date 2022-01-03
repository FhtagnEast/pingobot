package com.withlava.pingobot.notification;

public class Notification {
    private final long userId;
    private final long chatId;
    private final long updateCollectorMessageId;

    private final String description;

    private final Status status;
    private final DelayInfo delayInfo;
    private final ExecutionInfo executionInfo;
    private final LifecycleInfo lifecycleInfo;

    public Notification(
            long userId,
            long chatId,
            long updateCollectorMessageId,
            String description,
            Status status,
            DelayInfo delayInfo,
            ExecutionInfo executionInfo,
            LifecycleInfo lifecycleInfo)
    {
        this.userId = userId;
        this.chatId = chatId;
        this.updateCollectorMessageId = updateCollectorMessageId;
        this.description = description;
        this.status = status;
        this.delayInfo = delayInfo;
        this.executionInfo = executionInfo;
        this.lifecycleInfo = lifecycleInfo;
    }

    public long getUserId() {
        return userId;
    }

    public long getChatId() {
        return chatId;
    }

    public long getUpdateCollectorMessageId() {
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
