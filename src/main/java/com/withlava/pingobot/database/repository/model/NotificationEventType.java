package com.withlava.pingobot.database.repository.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum NotificationEventType {
    CREATED("created"),
    ACTIVATED("activated"),
    DEACTIVATED("deactivated"),
    DELETED("deleted"),
    NOTIFIED("notified"),
    DONE("done"),
    UNKNOWN_EVENT("unknown_event")
    ;
    
    private final String name;
    
    NotificationEventType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static final Map<String, NotificationEventType> BY_NAME = Collections.unmodifiableMap(
            Arrays
                    .stream(NotificationEventType.values())
                    .collect(Collectors.toMap(NotificationEventType::getName, e -> e)));

    public static Optional<NotificationEventType> getByName(String name) {
        return Optional.ofNullable(BY_NAME.get(name));
    }
}
