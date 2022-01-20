package com.withlava.pingobot.database.repository;

import com.withlava.pingobot.database.repository.dto.Notification;

import java.util.List;

public interface NotificationRepository {

    int create(Notification notification);

    int update(Notification notification);

    List<Notification> allActive();

    List<Notification> byUpdateMessageId(long id);

    List<Notification> byUserId(long userId);

    Notification byId(long id);

}
