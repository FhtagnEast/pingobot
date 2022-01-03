package com.withlava.pingobot.converters;

import com.withlava.pingobot.model.DelayInfo;
import com.withlava.pingobot.model.ExecutionInfo;
import com.withlava.pingobot.model.LifecycleInfo;
import com.withlava.pingobot.model.Notification;
import com.withlava.pingobot.model.Status;
import com.withlava.pingobot.repository.dto.PlainNotification;

import java.util.List;
import java.util.stream.Collectors;

public final class PlainConverter {
    private PlainConverter() {
    }

    public static Notification convert(PlainNotification plain) {
        return new Notification(
                plain.getId(),
                plain.getUserId(),
                plain.getChatId(),
                plain.getUpdateCollectorMessageId(),
                plain.getDescription(),
                new Status(
                        plain.isActive(),
                        plain.isDeleted()),
                new DelayInfo(
                        plain.getOnCompletedDelay(),
                        plain.getOnUncompletedDelay()),
                new ExecutionInfo(
                        plain.getNextExecutionTime(),
                        plain.getLastExecutionTime()),
                new LifecycleInfo(
                        plain.getCreated(),
                        plain.getMarkedOnDeletion())
        );
    }

    public static List<Notification> convert(List<PlainNotification> plainNotifications) {
        return plainNotifications.stream().map(PlainConverter::convert).collect(Collectors.toList());
    }
}
