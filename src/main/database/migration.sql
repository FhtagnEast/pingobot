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
    next_execution BIGINT,
    last_execution BIGINT,
    last_completed BIGINT,
    created BIGINT,
    marked_on_deletion BIGINT
);

CREATE TABLE IF NOT EXISTS update_message_ids
(
    notifications_id BIGINT,
    update_collector_message_id BIGINT,
    FOREIGN KEY (notifications_id) REFERENCES notifications (id)
);