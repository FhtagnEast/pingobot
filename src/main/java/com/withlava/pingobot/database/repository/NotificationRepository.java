package com.withlava.pingobot.database.repository;

import com.withlava.pingobot.database.repository.model.Notification;

import java.util.List;

public interface NotificationRepository {

    long create(Notification notification);

    void update(Notification notification);

    List<Notification> allActive();

    List<Notification> byUpdateMessageId(long id);

    List<Notification> byUserId(long userId);

    Notification byId(long id);

}
