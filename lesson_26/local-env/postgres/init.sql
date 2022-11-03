CREATE TABLE users
(
    id         BIGSERIAL NOT NULL UNIQUE,
    name       VARCHAR   NOT NULL UNIQUE,
    role       VARCHAR   NOT NULL,
    password   VARCHAR   NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);

INSERT INTO users (name, role, password)
VALUES ('test', 'EMPLOYEE', '123');
INSERT INTO users (name, role, password)
VALUES ('test1', 'MANAGER', '123');
INSERT INTO users (name, role, password)
VALUES ('test2', 'ADMIN', '123');
