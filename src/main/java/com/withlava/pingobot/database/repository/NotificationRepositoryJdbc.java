package com.withlava.pingobot.database.repository;

import com.withlava.pingobot.database.repository.dto.Notification;
import org.springframework.jdbc.core.DataClassRowMapper;
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
                "created," +
                "marked_on_deletion) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        return jdbcTemplate.update(
                sqlRequest,
                notification.getUserId(),
                notification.getChatId(),
                notification.getDescription(),
                notification.isActive(),
                notification.isDeleted(),
                notification.getOnCompletedDelay(),
                notification.getOnUncompletedDelay(),
                notification.getNextExecutionTime(),
                notification.getLastExecutionTime(),
                notification.getCreated(),
                notification.getMarkedOnDeletion());
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
                "created=?," +
                "marked_on_deletion=?) " +
                "WHERE id=?)";

        return jdbcTemplate.update(
                sqlRequest,
                notification.getUserId(),
                notification.getChatId(),
                notification.getDescription(),
                notification.isActive(),
                notification.isDeleted(),
                notification.getOnCompletedDelay(),
                notification.getOnUncompletedDelay(),
                notification.getNextExecutionTime(),
                notification.getLastExecutionTime(),
                notification.getCreated(),
                notification.getMarkedOnDeletion());
    }

    @Override
    // User id?
    public List<Notification> allActive() {
        String sqlRequest = "SELECT * FROM notifications WHERE active=true";

        return jdbcTemplate.query(
                sqlRequest,
                DataClassRowMapper.newInstance(Notification.class));
    }

    @Override
    public List<Notification> addActiveByUserId(long userId) {
        String sqlRequest = "SELECT * FROM notifications WHERE active=true AND user_id=?";

        return jdbcTemplate.query(
                sqlRequest,
                DataClassRowMapper.newInstance(Notification.class),
                userId);
    }

    @Override
    public List<Notification> byUpdateMessageId(long messageId) {
        String sqlRequest = "SELECT * FROM notifications WHERE update_collector_message_id=?";

        return jdbcTemplate.query(
                sqlRequest,
                DataClassRowMapper.newInstance(Notification.class),
                messageId);
    }

    @Override
    public List<Notification> byUserId(long userId) {
        String sqlRequest = "SELECT * FROM notifications WHERE user_id=?";

        //TODO: Could it be immutable?
        return jdbcTemplate.query(
                sqlRequest,
                DataClassRowMapper.newInstance(Notification.class),
                userId);
    }

    @Override
    public Notification byId(long id) {
        String sqlRequest = "SELECT * FROM notifications WHERE id=?";

        return jdbcTemplate.queryForObject(
                sqlRequest,
                DataClassRowMapper.newInstance(Notification.class),
                id);
    }
}

