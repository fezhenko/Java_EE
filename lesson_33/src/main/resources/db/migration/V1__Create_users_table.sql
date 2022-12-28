CREATE TABLE IF NOT EXISTS appUsers
(
    user_id BIGSERIAL NOT NULL UNIQUE,
    name VARCHAR NOT NULL,
    role VARCHAR NOT NULL CHECK (role in ('ADMIN', 'MANAGER', 'EMPLOYEE')),
    password VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (user_id)
);

INSERT INTO appUsers (name, role, password)
VALUES ('Employee', 'EMPLOYEE', '123');
INSERT INTO appUsers (name, role, password)
VALUES ('Manager', 'MANAGER', '123');
INSERT INTO appUsers (name, role, password)
VALUES ('Admin', 'ADMIN', '123');
