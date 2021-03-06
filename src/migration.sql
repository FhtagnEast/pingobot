CREATE TABLE IF NOT EXISTS notifications
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    user_id BIGINT,
    chat_id BIGINT,
    description VARCHAR(255),
    active BOOLEAN,
    deleted BOOLEAN,
    on_completed_delay BIGINT,
    on_uncompleted_delay BIGINT,
    next_execution_time BIGINT,
    last_execution_time BIGINT,
    created BIGINT,
    marked_on_deletion BIGINT
);

CREATE TABLE IF NOT EXISTS notification_history
(
    notification_id BIGINT,
    timestamp BIGINT,
    action VARCHAR(255),
    FOREIGN KEY (notification_id) REFERENCES notifications (id)
)