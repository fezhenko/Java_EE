CREATE TABLE IF NOT EXISTS users
(
    id         BIGSERIAL NOT NULL UNIQUE,
    name       VARCHAR   NOT NULL UNIQUE,
    role       VARCHAR   NOT NULL,
    password   VARCHAR   NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (id)
);

INSERT INTO users (name, role, password)
VALUES ('Employee', 'EMPLOYEE', '123');
INSERT INTO users (name, role, password)
VALUES ('Manager', 'MANAGER', '123');
INSERT INTO users (name, role, password)
VALUES ('Admin', 'ADMIN', '123');