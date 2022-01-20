package com.withlava.pingobot;

import com.withlava.pingobot.bot.Pingobot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.BotSession;

public class Application {
    private Application() {
    }

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String... args) {
        ApiContextInitializer.init();
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("classpath:config/bot-context.xml");
        context.refresh();
        Pingobot bot = context.getBean(Pingobot.class);
        TelegramBotsApi api = new TelegramBotsApi();
        try {
            BotSession botSession = api.registerBot(bot);

        } catch (TelegramApiRequestException e) {
            logger.warn("Exception catched while try to register bot.", e);
        }
    }

}
