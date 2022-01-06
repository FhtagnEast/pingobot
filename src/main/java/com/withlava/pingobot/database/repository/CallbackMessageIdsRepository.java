package com.withlava.pingobot.database.repository;

import java.util.List;

public interface CallbackMessageIdsRepository {

    int addUpdateCollectorMessageId(int messageId);

    int addEditMessageId(int messageId);

    List<Integer> getAllUpdateCollectorMessageId(Long notificationId);

    List<Integer> getAllEditMessageId(Long notificationId);

    Long getNotificationIdByUpdateCollectorMessageId(Long updateCollectorMssageId);

    Long getNotificationIdByEditMessageId(Long editMessageId);
}
