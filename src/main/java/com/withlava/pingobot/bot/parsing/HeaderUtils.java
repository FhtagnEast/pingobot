package com.withlava.pingobot.bot.parsing;

import org.springframework.util.StringUtils;

import java.util.Arrays;

public class HeaderUtils {
    private HeaderUtils() {
    }

    public static String extractHeader(String message) {
        String[] splittedMessage = trimAndSplitByWhitespace(message);
        return splittedMessage[0];
    }

    public static String removeHeader(String message) {
        String[] splittedMessage = trimAndSplitByWhitespace(message);

        int length = splittedMessage.length;
        if (length > 0) {
            splittedMessage = Arrays.copyOfRange(splittedMessage, 1, splittedMessage.length);
        }
        return StringUtils.arrayToDelimitedString(splittedMessage, " ");
    }

    private static String[] trimAndSplitByWhitespace(String message) {
        String trimmedMessage = StringUtils.trimLeadingWhitespace(message);
        return trimmedMessage.split(" ");
    }
}
