package com.withlava.pingobot.database.repository;

import com.withlava.pingobot.database.repository.model.Notification;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class NotificationRepositoryJdbc implements NotificationRepository {

    private final JdbcTemplate jdbcTemplate;

    public NotificationRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long create(Notification notification) {
        String sqlRequest = "INSERT INTO notifications (" +
                "user_id," +
                "description," +
                "active," +
                "deleted," +
                "on_completed_delay," +
                "on_uncompleted_delay," +
                "next_execution_time," +
                "last_execution_time," +
                "marked_on_deletion) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
                    ArgumentPreparedStatementSetter argumentPreparedStatementSetter = new ArgumentPreparedStatementSetter(new Object[]{
                            notification.getUserId(),
                            notification.getDescription(),
                            notification.isActive(),
                            notification.isDeleted(),
                            notification.getOnCompletedDelay().orElse(null),
                            notification.getOnUncompletedDelay().orElse(null),
                            notification.getNextExecutionTime().orElse(null),
                            notification.getLastExecutionTime().orElse(null),
                            notification.getMarkedOnDeletion().orElse(null)
                    });
                    argumentPreparedStatementSetter.setValues(preparedStatement);
                    return preparedStatement;
                },
                keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    //TODO: Update fields separately?
    public int update(Notification notification) {
        String sqlRequest = "UPDATE notifications SET" +
                "user_id=?," +
                "description=?," +
                "active=?," +
                "deleted=?," +
                "on_completed_delay=?," +
                "on_uncompleted_delay=?," +
                "next_execution_time=?," +
                "last_execution_time=?," +
                "marked_on_deletion=?) " +
                "WHERE id=?)";

        return jdbcTemplate.update(
                sqlRequest,
                notification.getUserId(),
                notification.getDescription(),
                notification.isActive(),
                notification.isDeleted(),
                notification.getOnCompletedDelay().orElse(null),
                notification.getOnUncompletedDelay().orElse(null),
                notification.getNextExecutionTime().orElse(null),
                notification.getLastExecutionTime().orElse(null),
                notification.getMarkedOnDeletion().orElse(null));
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

