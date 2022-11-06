CREATE TABLE IF NOT EXISTS requests
(
    request_id BIGSERIAL NOT NULL UNIQUE,
    request_user_id BIGSERIAL NOT NULL CHECK (request_user_id != received_user_id),
    received_user_id BIGSERIAL NOT NULL,
    isApproved BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (request_id),
    FOREIGN KEY (request_user_id) REFERENCES users (user_id),
    FOREIGN KEY (received_user_id) REFERENCES users (user_id)
);
-- TODO: изменить проект для использования новых названий колонок для реквестов
-- (
--     id BIGSERIAL NOT NULL UNIQUE,
--     user_id BIGSERIAL NOT NULL,
--     request_status VARCHAR NOT NULL CHECK (request_status in ('Incoming', 'Outcoming')),
--     created_at TIMESTAMP NOT NULL DEFAULT now(),
--     PRIMARY KEY (id),
--     FOREIGN KEY (user_id) REFERENCES users (id)
-- );

INSERT INTO requests (request_user_id, received_user_id, isApproved)
VALUES (1,2,true);
INSERT INTO requests (request_user_id, received_user_id, isApproved)
VALUES (1,3,false);
INSERT INTO requests (request_user_id, received_user_id, isApproved)
VALUES (2,1,false);
INSERT INTO requests (request_user_id, received_user_id, isApproved)
VALUES (3,2,true);

--
-- CREATE OR REPLACE FUNCTION check_is_user_already_sent_request()
--     RETURNS TRIGGER
--     LANGUAGE PLPGSQL
-- AS
-- $$
-- begin
--     if count(requests.request_user_id)
--     then
--         return null;
--     end if;
--     return requests;
-- end
-- $$;
--
-- CREATE TRIGGER is_user_already_sent_request
--     BEFORE UPDATE
--     ON requests
--     FOR EACH ROW
-- EXECUTE FUNCTION check_is_user_already_sent_request();
