package com.withlava.pingobot.database.repository;

import com.withlava.pingobot.database.repository.model.NotificationEvent;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class EventsRepositoryJdbc implements EventsRepository {

    private final JdbcTemplate jdbcTemplate;

    public EventsRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addEvent(NotificationEvent event) {
        String sqlRequest = "INSERT INTO notification_events (" +
                "notification_id," +
                "timestamp," +
                "event_type)" +
                "VALUES(?,?,?)";

        return jdbcTemplate.update(
                sqlRequest,
                event.getNotificationId(),
                event.getTimestamp(),
                event.getEventType().getName());
    }

    @Override
    public List<NotificationEvent> getAllById(long id) {
        String sqlRequest = "SELECT * FROM notification_events WHERE notification_id=?";

        return jdbcTemplate.query(sqlRequest, DataClassRowMapper.newInstance(NotificationEvent.class), id);
    }
}
