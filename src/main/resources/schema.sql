CREATE TABLE IF NOT EXISTS leg_exercise (
    id INT NOT NULL,
    leg_exercise_type varchar(250) NOT NULL,
    started_on timestamp NOT NULL,
    count INT NOT NULL,
    version INT NOT NULL,
    PRIMARY KEY (id)
);
