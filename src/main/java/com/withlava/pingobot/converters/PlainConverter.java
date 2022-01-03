package com.withlava.pingobot.converters;

import com.withlava.pingobot.database.model.DelayInfo;
import com.withlava.pingobot.database.model.ExecutionInfo;
import com.withlava.pingobot.database.model.LifecycleInfo;
import com.withlava.pingobot.database.model.Notification;
import com.withlava.pingobot.database.model.Status;
import com.withlava.pingobot.database.repository.dto.PlainNotification;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class PlainConverter {
    private PlainConverter() {
    }

    public static Notification convert(PlainNotification plain) {
        return new Notification(
                plain.getId(),
                plain.getUserId(),
                plain.getChatId(),
                Optional.ofNullable(plain.getUpdateCollectorMessageId()),
                Optional.ofNullable(plain.getEditMessageId()),
                plain.getDescription(),
                new Status(
                        plain.isActive(),
                        plain.isDeleted()),
                new DelayInfo(
                        plain.getOnCompletedDelay(),
                        plain.getOnUncompletedDelay()),
                new ExecutionInfo(
                        plain.getNextExecutionTime(),
                        Optional.ofNullable(plain.getLastExecutionTime()),
                        Optional.ofNullable(plain.getLastCompletedTime())),
                new LifecycleInfo(
                        plain.getCreated(),
                        Optional.ofNullable(plain.getMarkedOnDeletion()))
        );
    }

    public static List<Notification> convert(List<PlainNotification> plainNotifications) {
        return plainNotifications.stream().map(PlainConverter::convert).collect(Collectors.toList());
    }
}
