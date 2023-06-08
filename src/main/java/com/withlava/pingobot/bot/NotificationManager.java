package com.withlava.pingobot.bot;

import com.withlava.pingobot.bot.parsing.MessageProcessor;
import com.withlava.pingobot.database.repository.EventsRepository;
import com.withlava.pingobot.database.repository.NotificationRepository;
import com.withlava.pingobot.database.repository.model.Notification;
import org.telegram.telegrambots.meta.api.objects.Message;

public class NotificationManager {

    private final EventsRepository eventsRepository;
    private final NotificationRepository notificationRepository;

    private final MessageProcessor messageProcessor;

    public NotificationManager(
            EventsRepository eventsRepository,
            NotificationRepository notificationRepository,
            MessageProcessor messageProcessor)
    {
        this.eventsRepository = eventsRepository;
        this.notificationRepository = notificationRepository;
        this.messageProcessor = messageProcessor;
    }

    public String addNotification(Message message) {
        Notification newNotification = messageProcessor.createNewNotification(message);
        Notification notification = notificationRepository.create(newNotification);

    }
}
