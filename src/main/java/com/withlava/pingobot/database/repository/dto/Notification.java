package com.withlava.pingobot.database.repository.dto;


@SuppressWarnings({"ConstructorWithTooManyParameters", "BooleanParameter"})
public class Notification {
    private final long id;

    private final long userId;
    private final long chatId;

    private final String description;

    private final boolean active;
    private final boolean deleted;

    private final long onCompletedDelay;
    private final long onUncompletedDelay;

    private final long nextExecutionTime;
    private final Long lastExecutionTime;

    private final long created;
    private final Long markedOnDeletion;

    public Notification(
            long id,
            long userId,
            long chatId,
            String description,
            boolean active,
            boolean deleted,
            long onCompletedDelay,
            long onUncompletedDelay,
            long nextExecutionTime,
            Long lastExecutionTime,
            long created,
            Long markedOnDeletion) {
        this.id = id;
        this.userId = userId;
        this.chatId = chatId;
        this.description = description;
        this.active = active;
        this.deleted = deleted;
        this.onCompletedDelay = onCompletedDelay;
        this.onUncompletedDelay = onUncompletedDelay;
        this.nextExecutionTime = nextExecutionTime;
        this.lastExecutionTime = lastExecutionTime;
        this.created = created;
        this.markedOnDeletion = markedOnDeletion;
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

    public boolean isActive() {
        return active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public long getOnCompletedDelay() {
        return onCompletedDelay;
    }

    public long getOnUncompletedDelay() {
        return onUncompletedDelay;
    }

    public long getNextExecutionTime() {
        return nextExecutionTime;
    }

    public Long getLastExecutionTime() {
        return lastExecutionTime;
    }

    public long getCreated() {
        return created;
    }

    public Long getMarkedOnDeletion() {
        return markedOnDeletion;
    }
}