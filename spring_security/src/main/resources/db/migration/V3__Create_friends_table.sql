CREATE TABLE IF NOT EXISTS friends
(
    id BIGSERIAL NOT NULL UNIQUE,
    friend_user_id BIGSERIAL NOT NULL,
    request_id BIGSERIAL NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (id),
    FOREIGN KEY (friend_user_id) REFERENCES users (user_id),
    FOREIGN KEY (request_id) REFERENCES requests (request_id)
);