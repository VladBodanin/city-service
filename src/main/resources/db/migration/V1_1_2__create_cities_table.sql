CREATE TABLE IF NOT EXISTS cities
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    created_at          TIMESTAMP    NOT NULL,
    updated_at          TIMESTAMP    NOT NULL,
    name                VARCHAR(255) NOT NULL,
    country_id          BIGINT       NOT NULL,
    description         TEXT,
    UNIQUE (name, country_id),
    FOREIGN KEY (country_id) REFERENCES countries (id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS "cities_country_id_idx" ON cities USING btree (country_id);
