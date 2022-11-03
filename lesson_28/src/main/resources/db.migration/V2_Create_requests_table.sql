CREATE TABLE IF NOT EXISTS requests
(
    id              BIGSERIAL NOT NULL UNIQUE,
    user_id         BIGSERIAL NOT NULL UNIQUE,
    request_status  VARCHAR   NOT NULL CHECK (request_status == 'Incoming' || request_status == 'Outcoming'),
    created_at      TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);