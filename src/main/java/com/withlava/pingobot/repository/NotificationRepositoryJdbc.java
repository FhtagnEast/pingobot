package com.withlava.pingobot.repository;

import com.withlava.pingobot.converters.PlainConverter;
import com.withlava.pingobot.model.Notification;
import com.withlava.pingobot.repository.dto.PlainNotification;
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
                    "id," +
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
                notification.getId(),
                notification.getUserId(),
                notification.getChatId(),
                notification.getUpdateCollectorMessageId(),
                notification.getDescription(),
                notification.getStatus().getActive(),
                notification.getStatus().getDeleted(),
                notification.getDelayInfo().getOnCompletedDelay(),
                notification.getDelayInfo().getOnUncompletedDelay(),
                notification.getExecutionInfo().getNextExecutionTime(),
                notification.getExecutionInfo().getLastExecutionTime(),
                notification.getLifecycleInfo().getCreated(),
                notification.getLifecycleInfo().getMarkedOnDeletion());
    }

    @Override
    public int update(Notification notification) {
        return jdbcTemplate.update(
                "UPDATE notifications (" +
                    "id," +
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
                notification.getId(),
                notification.getUserId(),
                notification.getChatId(),
                notification.getUpdateCollectorMessageId(),
                notification.getDescription(),
                notification.getStatus().getActive(),
                notification.getStatus().getDeleted(),
                notification.getDelayInfo().getOnCompletedDelay(),
                notification.getDelayInfo().getOnUncompletedDelay(),
                notification.getExecutionInfo().getNextExecutionTime(),
                notification.getExecutionInfo().getLastExecutionTime(),
                notification.getLifecycleInfo().getCreated(),
                notification.getLifecycleInfo().getMarkedOnDeletion());
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
