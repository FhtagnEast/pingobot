package com.withlava.pingobot.database.repository.model;


import java.util.Optional;

@SuppressWarnings({"ConstructorWithTooManyParameters", "BooleanParameter"})
public class Notification {
    private final long id;

    private final long userId;

    private final String description;

    private final boolean active;
    private final boolean deleted;

    //TODO: you can store cron schedule here
    private final Optional<Long> onCompletedDelay; //add ms to name
    private final Optional<Long> onUncompletedDelay;

    //TODO: try extract this shit to kind of log
    // it depends on delay
    private final Optional<Long> nextExecutionTime;
    private final Optional<Long> lastExecutionTime;

    private final Optional<Long> markedOnDeletion;

    public Notification(
            long id,
            long userId,
            String description,
            boolean active,
            boolean deleted,
            Long onCompletedDelay,
            Long onUncompletedDelay,
            Long nextExecutionTime,
            Long lastExecutionTime,
            Long markedOnDeletion) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.active = active;
        this.deleted = deleted;
        this.onCompletedDelay = Optional.ofNullable(onCompletedDelay);
        this.onUncompletedDelay = Optional.ofNullable(onUncompletedDelay);
        this.nextExecutionTime = Optional.ofNullable(nextExecutionTime);
        this.lastExecutionTime = Optional.ofNullable(lastExecutionTime);
        this.markedOnDeletion = Optional.ofNullable(markedOnDeletion);
    }

    private Notification(long id, long userId, String description, boolean active, boolean deleted, Optional<Long> onCompletedDelay, Optional<Long> onUncompletedDelay, Optional<Long> nextExecutionTime, Optional<Long> lastExecutionTime, Optional<Long> markedOnDeletion) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.active = active;
        this.deleted = deleted;
        this.onCompletedDelay = onCompletedDelay;
        this.onUncompletedDelay = onUncompletedDelay;
        this.nextExecutionTime = nextExecutionTime;
        this.lastExecutionTime = lastExecutionTime;
        this.markedOnDeletion = markedOnDeletion;
    }

    private Notification() {

    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
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

    public Optional<Long> getOnCompletedDelay() {
        return onCompletedDelay;
    }

    public Optional<Long> getOnUncompletedDelay() {
        return onUncompletedDelay;
    }

    public Optional<Long> getNextExecutionTime() {
        return nextExecutionTime;
    }

    public Optional<Long> getLastExecutionTime() {
        return lastExecutionTime;
    }

    public Optional<Long> getMarkedOnDeletion() {
        return markedOnDeletion;
    }

    public Notification withId(long newId) {
        return new Notification(
                newId,
                userId,
                description,
                active,
                deleted,
                onCompletedDelay,
                onUncompletedDelay,
                nextExecutionTime,
                lastExecutionTime,
                markedOnDeletion
        )
    }
}