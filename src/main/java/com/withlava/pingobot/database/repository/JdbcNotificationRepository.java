package com.withlava.pingobot.database.repository;

import com.withlava.pingobot.converters.PlainConverter;
import com.withlava.pingobot.database.model.Notification;
import com.withlava.pingobot.database.repository.dto.PlainNotification;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcNotificationRepository implements NotificationRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcNotificationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Notification notification) {
        String sqlRequest = "INSERT INTO notifications (" +
                "user_id," +
                "chat_id," +
                "description," +
                "active," +
                "deleted," +
                "on_completed_delay," +
                "on_uncompleted_delay," +
                "next_execution," +
                "last_execution," +
                "last_completed," +
                "created," +
                "marked_on_deletion) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        return jdbcTemplate.update(
                sqlRequest,
                notification.getUserId(),
                notification.getChatId(),
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
    //TODO: Update fields separately?
    public int update(Notification notification) {
        String sqlRequest = "UPDATE notifications SET" +
                "user_id=?," +
                "chat_id=?," +
                "description=?," +
                "active=?," +
                "deleted=?," +
                "on_completed_delay=?," +
                "on_uncompleted_delay=?," +
                "next_execution=?," +
                "last_execution=?," +
                "last_completed=?" +
                "created=?," +
                "marked_on_deletion=?) " +
                "WHERE id=?)";

        return jdbcTemplate.update(
                sqlRequest,
                notification.getUserId(),
                notification.getChatId(),
                notification.getDescription(),
                notification.getStatus().isActive(),
                notification.getStatus().isDeleted(),
                notification.getDelayInfo().getOnCompletedDelay(),
                notification.getDelayInfo().getOnUncompletedDelay(),
                notification.getExecutionInfo().getNextExecutionTime(),
                notification.getExecutionInfo().getLastExecutionTime().orElse(null),
                notification.getExecutionInfo().getLastCompletedTime().orElse(null),
                notification.getLifecycleInfo().getCreated(),
                notification.getLifecycleInfo().getMarkedOnDeletion().orElse(null));
    }

    @Override
    // User id?
    public List<Notification> allActive() {
        String sqlRequest = "SELECT * FROM notifications WHERE active=true";

        List<PlainNotification> plainList = jdbcTemplate.query(
                sqlRequest,
                BeanPropertyRowMapper.newInstance(PlainNotification.class));
        return PlainConverter.convert(plainList);
    }

    @Override
    public List<Notification> addActiveByUserId(long userId) {
        String sqlRequest = "SELECT * FROM notifications WHERE active=true AND user_id=?";

        List<PlainNotification> plainNotifications = jdbcTemplate.query(
                sqlRequest,
                BeanPropertyRowMapper.newInstance(PlainNotification.class),
                userId);

        return PlainConverter.convert(plainNotifications);
    }

    @Override
    public List<Notification> byUpdateMessageId(long messageId) {
        String sqlRequest = "SELECT * FROM notifications WHERE update_collector_message_id=?";

        List<PlainNotification> plainList = jdbcTemplate.query(
                sqlRequest,
                BeanPropertyRowMapper.newInstance(PlainNotification.class),
                messageId);
        return PlainConverter.convert(plainList);
    }

    @Override
    public List<Notification> byUserId(long userId) {
        String sqlRequest = "SELECT * FROM notifications WHERE user_id=?";

        //TODO: Could it be immutable?
        List<PlainNotification> plainList = jdbcTemplate.query(
                sqlRequest,
                BeanPropertyRowMapper.newInstance(PlainNotification.class),
                userId);
        return PlainConverter.convert(plainList);
    }

    @Override
    public Notification byId(long id) {
        String sqlRequest = "SELECT * FROM notifications WHERE id=?";

        PlainNotification plain = jdbcTemplate.queryForObject(
                sqlRequest,
                BeanPropertyRowMapper.newInstance(PlainNotification.class),
                id);
        return PlainConverter.convert(plain);
    }
}
