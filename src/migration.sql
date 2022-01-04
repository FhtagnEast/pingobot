CREATE TABLE IF NOT EXISTS notifications
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    user_id BIGINT,
    chat_id BIGINT,
    update_collector_message_id BIGINT,
--     edit_message_id BIGINT,
    description VARCHAR(255),
    active BOOLEAN,
    deleted BOOLEAN,
    on_completed_delay BIGINT,
    on_uncompleted_delay BIGINT,
    next_execution BIGINT,
    last_execution BIGINT,
    created BIGINT,
    marked_on_deletion BIGINT
);

CREATE TABLE IF NOT EXISTS edit_message_ids
(
    notification_id BIGINT,
    edit_message_id BIGINT,
    FOREIGN KEY (notification_id) REFERENCES notifications (id)
);