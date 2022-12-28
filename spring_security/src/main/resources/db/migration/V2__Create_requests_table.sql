CREATE TABLE IF NOT EXISTS requests
(
    request_id BIGSERIAL NOT NULL UNIQUE,
    request_user_id BIGSERIAL NOT NULL CHECK (request_user_id != received_user_id),
    received_user_id BIGSERIAL NOT NULL,
    is_approved BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (request_id),
    FOREIGN KEY (request_user_id) REFERENCES users (user_id),
    FOREIGN KEY (received_user_id) REFERENCES users (user_id)
);

INSERT INTO requests (request_user_id, received_user_id, is_approved)
VALUES (1,2,true);
INSERT INTO requests (request_user_id, received_user_id, is_approved)
VALUES (1,3,false);
INSERT INTO requests (request_user_id, received_user_id, is_approved)
VALUES (2,1,false);
INSERT INTO requests (request_user_id, received_user_id, is_approved)
VALUES (3,2,true);