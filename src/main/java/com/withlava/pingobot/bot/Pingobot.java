package com.withlava.pingobot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

public class Pingobot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(Pingobot.class);

    private final UpdateHandler updateHandler;

    private final String botToken;

    public Pingobot(UpdateHandler updateHandler, String botToken) {
        this.updateHandler = updateHandler;
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        List<SendMessage> messages = updateHandler.handleUpdate(update);

        if (messages.isEmpty()) {
            logger.info("Send messages are empty for update {}", update);
        } else {
            executeAll(messages);
        }
    }

    public void executeAll(List<SendMessage> messages) {
        messages.forEach(m -> {
            try {
                execute(m);
            } catch (TelegramApiException e) {
                logger.warn("Exception caught while trying to execute message {}.", m, e);
            }
        });
    }

    @Override
    public String getBotUsername() {
        return "PingoBot";
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
