package com.withlava.pingobot.database.repository;

import com.withlava.pingobot.database.repository.model.NotificationEvent;

import java.util.List;

public interface EventsRepository {
    int addEvent(NotificationEvent event);

    List<NotificationEvent> getAllById(long id);
}
