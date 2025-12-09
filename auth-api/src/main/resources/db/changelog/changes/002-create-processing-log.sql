-- liquibase formatted sql

-- changeset artur:2
CREATE TABLE processing_log(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    input_text TEXT,
    output_text TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);
--rollback DROP TABLE processing_log;