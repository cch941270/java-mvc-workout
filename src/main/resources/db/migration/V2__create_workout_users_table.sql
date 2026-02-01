CREATE TABLE IF NOT EXISTS workout_users (
    id BIGINT NOT NULL,
    email varchar(250) UNIQUE NOT NULL,
    hashed_password varchar(250) NOT NULL,
    PRIMARY KEY (id)
);
CREATE SEQUENCE IF NOT EXISTS workout_users_id_seq START 1 INCREMENT 1;
