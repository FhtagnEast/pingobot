package com.withlava.pingobot.bot.parsing;

public class HeaderExtractor {
    private HeaderExtractor() {
    }

    public static String extractHeader(String message) {
        String[] splittedMessage = message.split(" ");
        return splittedMessage[0];
    }
}
