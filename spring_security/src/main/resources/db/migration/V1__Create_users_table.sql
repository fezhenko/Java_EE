CREATE TABLE IF NOT EXISTS users
(
    user_id BIGSERIAL NOT NULL UNIQUE,
    name VARCHAR NOT NULL,
    role VARCHAR NOT NULL CHECK (role in ('ADMIN', 'MANAGER', 'EMPLOYEE')),
    password VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (user_id)
);

-- password: 123
INSERT INTO users (name, role, password)
VALUES ('Employee', 'EMPLOYEE', '$2a$10$cWdjJViYxnr6BL4BujyZv.6jZgDhf9t3o7N0ww5Nn3SdBcoq.hXPG');
INSERT INTO users (name, role, password)
VALUES ('Manager', 'MANAGER', '$2a$10$cWdjJViYxnr6BL4BujyZv.6jZgDhf9t3o7N0ww5Nn3SdBcoq.hXPG');
INSERT INTO users (name, role, password)
VALUES ('Admin', 'ADMIN', '$2a$10$cWdjJViYxnr6BL4BujyZv.6jZgDhf9t3o7N0ww5Nn3SdBcoq.hXPG');
