package com.withlava.pingobot.repository;

import com.withlava.pingobot.model.Notification;

import java.util.List;

public interface NotificationRepository {

    int create(Notification notification);

    int update(Notification notification);

    List<Notification> byUserId(long userId);

    Notification byId(long id);

}
