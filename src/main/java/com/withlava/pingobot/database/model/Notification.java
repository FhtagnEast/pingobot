package com.withlava.pingobot.database.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Notification {
    private final long id;

    private final long userId;
    private final long chatId;
    private final List<Long> updateCollectorMessageId;

    private final String description;

    private final Status status;
    private final DelayInfo delayInfo;
    private final ExecutionInfo executionInfo;
    private final LifecycleInfo lifecycleInfo;

    public Notification(
            long id,
            long userId,
            long chatId,
            List<Long> updateCollectorMessageId,
            String description,
            Status status,
            DelayInfo delayInfo,
            ExecutionInfo executionInfo,
            LifecycleInfo lifecycleInfo)
    {
        this.id = id;
        this.userId = userId;
        this.chatId = chatId;
        this.updateCollectorMessageId = Collections.unmodifiableList(updateCollectorMessageId);
        this.description = description;
        this.status = status;
        this.delayInfo = delayInfo;
        this.executionInfo = executionInfo;
        this.lifecycleInfo = lifecycleInfo;
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

    public List<Long> getUpdateCollectorMessageId() {
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
