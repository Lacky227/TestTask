--liquibase formatted sql

--changeset artur:1
CREATE TABLE users(
    id UUID PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL
);
-- rollback DROP TABLE users;