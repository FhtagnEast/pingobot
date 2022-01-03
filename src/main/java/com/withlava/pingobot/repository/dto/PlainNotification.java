package com.withlava.pingobot.repository.dto;

public class PlainNotification {
    private long id;

    private long userId;
    private long chatId;
    private long updateCollectorMessageId;

    private String description;

    private boolean active;
    private boolean deleted;

    private long onCompletedDelay;
    private long onUncompletedDelay;

    private long nextExecutionTime;
    private long lastExecutionTime;

    private long created;
    private long markedOnDeletion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public long getUpdateCollectorMessageId() {
        return updateCollectorMessageId;
    }

    public void setUpdateCollectorMessageId(long updateCollectorMessageId) {
        this.updateCollectorMessageId = updateCollectorMessageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getOnCompletedDelay() {
        return onCompletedDelay;
    }

    public void setOnCompletedDelay(long onCompletedDelay) {
        this.onCompletedDelay = onCompletedDelay;
    }

    public long getOnUncompletedDelay() {
        return onUncompletedDelay;
    }

    public void setOnUncompletedDelay(long onUncompletedDelay) {
        this.onUncompletedDelay = onUncompletedDelay;
    }

    public long getLastExecutionTime() {
        return lastExecutionTime;
    }

    public void setLastExecutionTime(long lastExecutionTime) {
        this.lastExecutionTime = lastExecutionTime;
    }

    public long getNextExecutionTime() {
        return nextExecutionTime;
    }

    public void setNextExecutionTime(long nextExecutionTime) {
        this.nextExecutionTime = nextExecutionTime;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getMarkedOnDeletion() {
        return markedOnDeletion;
    }

    public void setMarkedOnDeletion(long markedOnDeletion) {
        this.markedOnDeletion = markedOnDeletion;
    }
}