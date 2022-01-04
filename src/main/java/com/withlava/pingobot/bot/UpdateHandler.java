package com.withlava.pingobot.bot;

import com.withlava.pingobot.bot.parsing.HeaderUtils;
import com.withlava.pingobot.database.model.DelayInfo;
import com.withlava.pingobot.database.model.ExecutionInfo;
import com.withlava.pingobot.database.model.LifecycleInfo;
import com.withlava.pingobot.database.model.Notification;
import com.withlava.pingobot.database.model.Status;
import com.withlava.pingobot.database.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UpdateHandler {
    private static final Logger logger = LoggerFactory.getLogger(UpdateHandler.class);

    private static final long DAY_IN_MILLIS = 24*60*60*1000;

    private final NotificationRepository notificationRepository;

    public UpdateHandler(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<SendMessage> handleUpdate(Update update) {
        Message inputMessage = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(inputMessage.getChatId());
        List<SendMessage> sendMessages = new ArrayList<>();
        if (isValid(update)) {
            String header = HeaderUtils.extractHeader(inputMessage.getText());
            switch (header) {
                case "/add":
                    sendMessages.add(create(inputMessage, sendMessage));
                    break;
                case "/all":
                    sendMessages.addAll(getAll(inputMessage));
                    break;
                default:
                    sendMessages.add(emptyInputAlert(sendMessage));
            }
        } else {
            sendMessage.setText("You trying to use bot from invalid chat type. The bot works only in private chats.");
            sendMessages.add(sendMessage);
        }
        return sendMessages;
    }

    private SendMessage create(Message inputMessage, SendMessage sendMessage) {
        notificationRepository.create(new Notification(
                -1,
                inputMessage.getFrom().getId(),
                inputMessage.getChat().getId(),
                Optional.empty(),
                HeaderUtils.removeHeader(inputMessage.getText()),
                new Status(true, false),
                new DelayInfo(DAY_IN_MILLIS, DAY_IN_MILLIS),
                new ExecutionInfo(
                        System.currentTimeMillis() + DAY_IN_MILLIS,
                        Optional.empty(),
                        Optional.empty()),
                new LifecycleInfo(System.currentTimeMillis(), Optional.empty())
        ));
        sendMessage.setText("Dummy notification with text " + inputMessage.getText() + " created");
        return sendMessage;
    }

    private List<SendMessage> getAll(Message inputMessage) {
        long chatId = inputMessage.getChatId();
        List<Notification> existingNotifications = notificationRepository.byUserId(inputMessage.getFrom().getId());
        return existingNotifications.stream().map(en -> {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(en.getDescription());
            return sendMessage;
        }).collect(Collectors.toList());
    }

    private SendMessage emptyInputAlert(SendMessage sendMessage) {
        sendMessage.setText("There are not valid tags in the head of the message.");
        return sendMessage;
    }

    private boolean isValid(Update update) {
        if(!update.getMessage().getChat().isUserChat()) {
            logger.info("Message from invalid chat type (non-private).");
            return false;
        }
        return true;
    }
}
