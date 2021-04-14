create table if not exists cities
(
    id bigint primary key GENERATED ALWAYS AS IDENTITY,
    created_at          TIMESTAMP    NOT NULL,
    updated_at          TIMESTAMP    NOT NULL,
    name                VARCHAR(255) NOT NULL,
    country_id          BIGINT       NOT NULL,
    description         TEXT,
    UNIQUE (name, country_id),
    FOREIGN KEY (country_id) REFERENCES countries (id) ON delete CASCADE
);

create index if not exists "cities_country_id_idx" on cities using btree (country_id);
