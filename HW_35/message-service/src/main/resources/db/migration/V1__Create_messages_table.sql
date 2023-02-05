CREATE TABLE IF NOT EXISTS messages
(
    message_id BIGSERIAL NOT NULL UNIQUE,
    message VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (message_id)
);