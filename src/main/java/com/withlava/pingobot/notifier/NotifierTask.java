package com.withlava.pingobot.notifier;

import com.withlava.pingobot.bot.Pingobot;
import com.withlava.pingobot.database.repository.NotificationRepository;
import com.withlava.pingobot.database.repository.model.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class NotifierTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(NotifierTask.class);

    private final NotificationRepository notificationRepository;

    private final AbsSender sender;

    public NotifierTask(NotificationRepository notificationRepository, Pingobot sender) {
        this.notificationRepository = notificationRepository;
        this.sender = sender;
    }

    @Override
    public void run() {
        notifyAllUsers();
    }

    private void notifyAllUsers() {
        logger.info("Notify started!");
        List<Notification> activeNotifications = notificationRepository.allActive();
        List<SendMessage> sendMessages = new ArrayList<>();
        long currentTimestamp = System.currentTimeMillis();
        activeNotifications.stream()
                .filter(an -> {
                    //TODO: Do it on database side
                    return !an.isDeleted()
                            && an.isActive()
                            && an.getNextExecutionTime() < currentTimestamp;
                })
                .forEach(an -> {
                    SendMessage message = new SendMessage();
                    message.setChatId(an.getChatId());
                    message.setText(an.getDescription());
                    try {
                        //TODO: Send message could be extracted to a single class to make this logic unified with update handler
                        Message sentMessage = sender.execute(message);
                    } catch (TelegramApiException e) {
                        logger.warn("Exception caught while trying to execute message {}.", message, e);
                    }
                });
    }
}
