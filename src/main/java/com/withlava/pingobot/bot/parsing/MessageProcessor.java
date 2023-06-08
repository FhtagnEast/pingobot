package com.withlava.pingobot.bot.parsing;

import com.withlava.pingobot.database.repository.model.Notification;
import org.telegram.telegrambots.meta.api.objects.Message;

public class MessageProcessor {

    private static final long DAY_IN_MILLIS = 24*60*60*1000;

    public Notification createNewNotification(Message inputMessage) {
        return new Notification(
                -1,
                inputMessage.getFrom().getId(),
                HeaderUtils.removeHeader(inputMessage.getText()),
                true,
                false,
                DAY_IN_MILLIS,
                DAY_IN_MILLIS,
                System.currentTimeMillis() + DAY_IN_MILLIS,
                null,
                null
        );
    }
}
