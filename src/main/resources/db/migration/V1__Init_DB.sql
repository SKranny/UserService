CREATE TABLE IF NOT EXISTS address (
    id                  BIGSERIAL PRIMARY KEY,
    city                VARCHAR(255) NOT NULL,
    country             VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS person (
    id                  BIGSERIAL PRIMARY KEY,
    email               VARCHAR(255) UNIQUE NOT NULL,
    phone               VARCHAR(255),
    photo               VARCHAR(255),
    about               VARCHAR(2048),
    address             BIGINT REFERENCES address(id),
    status_code         VARCHAR(255),
    first_name          VARCHAR(255),
    last_name           VARCHAR(255),
    birth_day           DATE,
    messages_permission VARCHAR(255),
    last_online_time    TIMESTAMP WITHOUT TIME ZONE,
    is_online           BOOLEAN,
    is_blocked          BOOLEAN DEFAULT false,
    is_deleted          BOOLEAN,
    created_on          TIMESTAMP WITHOUT TIME ZONE,
    updated_on          TIMESTAMP WITHOUT TIME ZONE,
    password            VARCHAR(255)
 );

CREATE TABLE IF NOT EXISTS role (
    id                  BIGSERIAL PRIMARY KEY,
    role                VARCHAR(255) NOT NULL
);

INSERT INTO role (role) VALUES ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_MODERATOR');

CREATE TABLE IF NOT EXISTS person2role (
    person_id           BIGINT NOT NULL REFERENCES person(id),
    role_id             BIGINT NOT NULL REFERENCES role(id)
);