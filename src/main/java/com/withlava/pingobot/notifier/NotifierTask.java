package com.withlava.pingobot.notifier;

import com.withlava.pingobot.bot.Pingobot;
import com.withlava.pingobot.database.model.Notification;
import com.withlava.pingobot.database.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Notification> activeNotifications = notificationRepository.allActive();
        List<SendMessage> sendMessages = new ArrayList<>();
        long currentTimestamp = System.currentTimeMillis();
        activeNotifications.stream()
                .filter(an -> {
                    return !an.getStatus().isDeleted()
                            && an.getStatus().isActive()
                            && an.getExecutionInfo().getNextExecutionTime() < currentTimestamp;
                })
                .forEach(an -> {
                    SendMessage message = new SendMessage();
                    message.setChatId(an.getChatId());
                    message.setText(an.getDescription());
                    try {
                        Message sentMessage = sender.execute(message);
                        notificationRepository.update(an.withCollectorMessageId(sentMessage.getMessageId()));
                    } catch (TelegramApiException e) {
                        logger.warn("Exception caught while trying to execute message {}.", message, e);
                    }
                });
    }
}
