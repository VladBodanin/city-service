CREATE TABLE IF NOT EXISTS countries
(
    id                  BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    created_at          TIMESTAMP    NOT NULL,
    updated_at          TIMESTAMP    NOT NULL,
    name                VARCHAR(255) NOT NULL UNIQUE
);
