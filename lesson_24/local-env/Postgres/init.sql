CREATE TABLE users
(
    name VARCHAR (20) NOT NULL UNIQUE,
    password VARCHAR (20) NOT NULL
);

INSERT INTO users (name,password)
VALUES ('Aliaksei','password');
