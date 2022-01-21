package com.withlava.pingobot.database.repository.model;

public class NotificationEvent {
    private final long notificationId;
    private final long timestamp;
    private final NotificationEventType eventType;

    public NotificationEvent(long notificationId, long timestamp, String eventType) {
        this.notificationId = notificationId;
        this.timestamp = timestamp;
        this.eventType =
                NotificationEventType.getByName(eventType).orElseGet(() -> NotificationEventType.UNKNOWN_EVENT);
    }

    public long getNotificationId() {
        return notificationId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public NotificationEventType getEventType() {
        return eventType;
    }
}
