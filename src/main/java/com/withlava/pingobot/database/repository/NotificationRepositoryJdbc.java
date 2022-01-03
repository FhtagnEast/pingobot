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
        return jdbcTemplate.update(
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
                notification.getUpdateCollectorMessageId().orElse(null),
                notification.getDescription(),
                notification.getStatus().isActive(),
                notification.getStatus().isDeleted(),
                notification.getDelayInfo().getOnCompletedDelay(),
                notification.getDelayInfo().getOnUncompletedDelay(),
                notification.getExecutionInfo().getNextExecutionTime(),
                notification.getExecutionInfo().getLastExecutionTime().orElse(null),
                notification.getLifecycleInfo().getCreated(),
                notification.getLifecycleInfo().getMarkedOnDeletion().orElse(null));
    }

    @Override
    public int update(Notification notification) {
        return jdbcTemplate.update(
                "UPDATE notifications SET" +
                    "user_id=?," +
                    "chat_id=?," +
                    "update_collector_message_id=?," +
                    "edit_message_id=?," +
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
                notification.getUpdateCollectorMessageId().orElse(null),
                notification.getDescription(),
                notification.getStatus().isActive(),
                notification.getStatus().isDeleted(),
                notification.getDelayInfo().getOnCompletedDelay(),
                notification.getDelayInfo().getOnUncompletedDelay(),
                notification.getExecutionInfo().getNextExecutionTime(),
                notification.getExecutionInfo().getLastExecutionTime().orElse(null),
                notification.getLifecycleInfo().getCreated(),
                notification.getLifecycleInfo().getMarkedOnDeletion().orElse(null));
    }

    @Override
    public List<Notification> allActive() {
        List<PlainNotification> plainList = jdbcTemplate.query(
                "SELECT * FROM notifications WHERE active=true",
                BeanPropertyRowMapper.newInstance(PlainNotification.class));
        return PlainConverter.convert(plainList);
    }

    @Override
    public List<Notification> byUpdateMessageId(long messageId) {
        List<PlainNotification> plainList = jdbcTemplate.query(
                "SELECT * FROM notifications WHERE update_collector_message_id=?",
                BeanPropertyRowMapper.newInstance(PlainNotification.class),
                messageId);
        return PlainConverter.convert(plainList);
    }

    @Override
    public List<Notification> byUserId(long userId) {
        List<PlainNotification> plainList = jdbcTemplate.query(
                "SELECT * FROM notifications WHERE user_id=?",
                BeanPropertyRowMapper.newInstance(PlainNotification.class),
                userId);
        return PlainConverter.convert(plainList);
    }

    @Override
    public Notification byId(long id) {
        PlainNotification plain = jdbcTemplate.queryForObject(
                "SELECT * FROM notifications WHERE id=?",
                BeanPropertyRowMapper.newInstance(PlainNotification.class),
                id);
        return PlainConverter.convert(plain);
    }
}
