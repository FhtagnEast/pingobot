package com.withlava.pingobot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Pingobot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(Pingobot.class);

    private final String botToken;

    public Pingobot(String botToken) {
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(update.getMessage().getText());
        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.warn("Exception caught while trying to execute message.", e);
        }
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
