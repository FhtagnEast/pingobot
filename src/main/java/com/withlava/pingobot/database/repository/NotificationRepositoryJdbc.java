package com.withlava.pingobot.database.repository;

import com.withlava.pingobot.converters.PlainConverter;
import com.withlava.pingobot.database.model.Notification;
import com.withlava.pingobot.database.repository.dto.PlainNotification;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepositoryJdbc implements NotificationRepository {

    private final JdbcTemplate jdbcTemplate;

    public NotificationRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Notification notification) {
        return jdbcTemplate.update(//TODO: fix join
                "INSERT INTO notifications (" +
                    "user_id," +
                    "chat_id," +
                    "update_collector_message_id," +
                    "description," +
                    "active," +
                    "deleted," +
                    "on_completed_delay," +
                    "on_uncompleted_delay," +
                    "next_execution," +
                    "last_execution," +
                    "created," +
                    "marked_on_deletion) " +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",
                notification.getUserId(),
                notification.getChatId(),
                notification.getUpdateCollectorMessageId(),
                notification.getDescription(),
                notification.getStatus().getActive(),
                notification.getStatus().getDeleted(),
                notification.getDelayInfo().getOnCompletedDelay(),
                notification.getDelayInfo().getOnUncompletedDelay(),
                notification.getExecutionInfo().getNextExecutionTime(),
                notification.getExecutionInfo().getLastExecutionTime().orElse(null),
                notification.getLifecycleInfo().getCreated(),
                notification.getLifecycleInfo().getMarkedOnDeletion().orElse(null));
    }

    @Override
    public int update(Notification notification) {
        return jdbcTemplate.update(//TODO: fix join
                "UPDATE notifications SET" +
                    "user_id=?," +
                    "chat_id=?," +
                    "update_collector_message_id=?," +
                    "description=?," +
                    "active=?," +
                    "deleted=?," +
                    "on_completed_delay=?," +
                    "on_uncompleted_delay=?," +
                    "next_execution=?," +
                    "last_execution=?," +
                    "created=?," +
                    "marked_on_deletion=?) " +
                    "WHERE id=?)",
                notification.getUserId(),
                notification.getChatId(),
                notification.getUpdateCollectorMessageId(),
                notification.getDescription(),
                notification.getStatus().getActive(),
                notification.getStatus().getDeleted(),
                notification.getDelayInfo().getOnCompletedDelay(),
                notification.getDelayInfo().getOnUncompletedDelay(),
                notification.getExecutionInfo().getNextExecutionTime(),
                notification.getExecutionInfo().getLastExecutionTime().orElse(null),
                notification.getLifecycleInfo().getCreated(),
                notification.getLifecycleInfo().getMarkedOnDeletion().orElse(null));
    }

    @Override
    public List<Notification> byUserId(long userId) {
        List<PlainNotification> plainList = jdbcTemplate.query(
                "SELECT * FROM notifications WHERE user_id=?", //TODO: fix join
                BeanPropertyRowMapper.newInstance(PlainNotification.class),
                userId);
        return PlainConverter.convert(plainList);
    }

    @Override
    public Notification byId(long id) {
        PlainNotification plain = jdbcTemplate.queryForObject(
                "SELECT * FROM notifications WHERE id=?", //TODO: fix join
                BeanPropertyRowMapper.newInstance(PlainNotification.class),
                id);
        return PlainConverter.convert(plain);
    }
}
