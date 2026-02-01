CREATE TABLE IF NOT EXISTS leg_exercises (
    id INT NOT NULL,
    leg_exercise_type varchar(250) NOT NULL,
    started_on timestamp NOT NULL,
    count INT NOT NULL,
    PRIMARY KEY (id)
);
CREATE SEQUENCE IF NOT EXISTS leg_exercises_id_seq START 1 INCREMENT 1;
