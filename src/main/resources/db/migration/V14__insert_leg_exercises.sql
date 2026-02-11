WITH user1 AS (
        SELECT id FROM workout_users WHERE email = 'alexander@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user1.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user1,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 10),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 12),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 15),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 8),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 10),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 50),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 80),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 15),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 20),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 12),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 15),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 100),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 120),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 25),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 20),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 150),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 30),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 25),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 200),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 35),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 30)
) AS data(id, leg_exercise_type, started_on, count);

WITH user2 AS (
        SELECT id FROM workout_users WHERE email = 'benjamin@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user2.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user2,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 12),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 14),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 17),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 10),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 12),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 60),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 90),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 17),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 22),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 14),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 17),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 110),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 130),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 27),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 22),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 160),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 32),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 27),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 210),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 37),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 32)
) AS data(id, leg_exercise_type, started_on, count);

WITH user3 AS (
        SELECT id FROM workout_users WHERE email = 'christopher@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user3.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user3,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 11),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 13),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 16),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 9),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 11),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 55),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 85),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 16),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 21),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 13),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 16),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 105),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 125),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 26),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 21),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 155),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 31),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 26),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 205),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 36),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 31)
) AS data(id, leg_exercise_type, started_on, count);

WITH user4 AS (
        SELECT id FROM workout_users WHERE email = 'daniel@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user4.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user4,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 13),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 15),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 18),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 11),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 13),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 65),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 95),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 18),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 23),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 15),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 18),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 115),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 135),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 28),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 23),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 165),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 33),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 28),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 215),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 38),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 33)
) AS data(id, leg_exercise_type, started_on, count);

WITH user5 AS (
        SELECT id FROM workout_users WHERE email = 'ethan@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user5.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user5,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 14),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 16),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 19),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 12),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 14),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 70),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 100),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 19),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 24),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 16),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 19),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 120),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 140),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 29),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 24),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 170),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 34),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 29),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 220),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 39),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 34)
) AS data(id, leg_exercise_type, started_on, count);

WITH user6 AS (
        SELECT id FROM workout_users WHERE email = 'felix@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user6.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user6,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 15),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 17),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 20),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 13),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 15),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 75),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 105),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 20),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 25),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 17),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 20),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 125),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 145),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 30),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 25),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 175),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 35),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 30),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 225),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 40),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 35)
) AS data(id, leg_exercise_type, started_on, count);

WITH user7 AS (
        SELECT id FROM workout_users WHERE email = 'gabriel@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user7.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user7,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 16),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 18),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 21),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 14),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 16),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 80),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 110),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 21),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 26),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 18),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 21),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 130),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 150),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 31),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 26),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 180),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 36),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 31),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 230),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 41),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 36)
) AS data(id, leg_exercise_type, started_on, count);

WITH user8 AS (
        SELECT id FROM workout_users WHERE email = 'henry@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user8.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user8,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 17),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 19),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 22),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 15),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 17),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 85),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 115),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 22),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 27),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 19),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 22),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 135),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 155),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 32),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 27),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 185),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 37),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 32),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 235),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 42),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 37)
) AS data(id, leg_exercise_type, started_on, count);

WITH user9 AS (
        SELECT id FROM workout_users WHERE email = 'isaac@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user9.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user9,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 18),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 20),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 23),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 16),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 18),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 90),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 120),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 23),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 28),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 20),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 23),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 140),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 160),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 33),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 28),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 190),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 38),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 33),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 240),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 43),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 38)
) AS data(id, leg_exercise_type, started_on, count);

WITH user10 AS (
        SELECT id FROM workout_users WHERE email = 'jacob@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user10.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user10,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 19),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 21),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 24),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 17),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 19),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 95),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 125),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 24),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 29),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 21),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 24),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 145),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 165),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 34),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 29),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 195),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 39),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 34),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 245),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 44),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 39)
) AS data(id, leg_exercise_type, started_on, count);

WITH user11 AS (
        SELECT id FROM workout_users WHERE email = 'kevin@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user11.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user11,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 20),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 22),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 25),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 18),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 20),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 100),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 130),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 25),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 30),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 22),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 25),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 150),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 170),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 35),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 30),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 200),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 40),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 35),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 250),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 45),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 40)
) AS data(id, leg_exercise_type, started_on, count);

WITH user12 AS (
        SELECT id FROM workout_users WHERE email = 'liam@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user12.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user12,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 21),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 23),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 26),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 19),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 21),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 105),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 135),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 26),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 31),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 23),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 26),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 155),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 175),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 36),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 31),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 205),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 41),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 36),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 255),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 46),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 41)
) AS data(id, leg_exercise_type, started_on, count);

WITH user13 AS (
        SELECT id FROM workout_users WHERE email = 'mason@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user13.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user13,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 22),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 24),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 27),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 20),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 22),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 110),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 140),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 27),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 32),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 24),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 27),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 160),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 180),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 37),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 32),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 210),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 42),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 37),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 260),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 47),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 42)
) AS data(id, leg_exercise_type, started_on, count);

WITH user14 AS (
        SELECT id FROM workout_users WHERE email = 'nathan@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user14.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user14,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 23),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 25),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 28),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 21),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 23),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 115),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 145),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 28),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 33),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 25),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 28),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 165),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 185),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 38),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 33),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 215),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 43),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 38),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 265),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 48),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 43)
) AS data(id, leg_exercise_type, started_on, count);

WITH user15 AS (
        SELECT id FROM workout_users WHERE email = 'oliver@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user15.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user15,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 24),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 26),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 29),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 22),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 24),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 120),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 150),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 29),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 34),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 26),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 29),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 170),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 190),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 39),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 34),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 220),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 44),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 39),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 270),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 49),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 44)
) AS data(id, leg_exercise_type, started_on, count);

WITH user16 AS (
        SELECT id FROM workout_users WHERE email = 'patrick@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user16.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user16,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 25),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 27),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 30),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 23),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 25),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 125),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 155),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 30),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 35),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 27),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 30),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 175),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 195),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 40),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 35),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 225),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 45),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 40),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 275),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 50),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 45)
) AS data(id, leg_exercise_type, started_on, count);

WITH user17 AS (
        SELECT id FROM workout_users WHERE email = 'quinn@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user17.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user17,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 26),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 28),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 31),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 24),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 26),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 130),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 160),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 31),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 36),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 28),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 31),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 180),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 200),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 41),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 36),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 230),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 46),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 41),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 280),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 51),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 46)
) AS data(id, leg_exercise_type, started_on, count);

WITH user18 AS (
        SELECT id FROM workout_users WHERE email = 'ryan@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user18.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user18,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 27),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 29),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 32),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 25),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 27),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 135),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 165),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 32),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 37),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 29),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 32),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 185),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 205),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 42),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 37),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 235),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 47),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 42),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 285),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 52),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 47)
) AS data(id, leg_exercise_type, started_on, count);

WITH user19 AS (
        SELECT id FROM workout_users WHERE email = 'samuel@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user19.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user19,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 28),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 30),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 33),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 26),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 28),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 140),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 170),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 33),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 38),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 30),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 33),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 190),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 210),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 43),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 38),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 240),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 48),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 43),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 290),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 53),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 48)
) AS data(id, leg_exercise_type, started_on, count);

WITH user20 AS (
        SELECT id FROM workout_users WHERE email = 'thomas@example.com' LIMIT 1
    )
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT user20.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM user20,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 29),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 31),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 34),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 27),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 29),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 145),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 175),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 34),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 39),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 31),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 34),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 195),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 215),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-30 10:00:00'::timestamp, 44),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-31 10:00:00'::timestamp, 39),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-01 10:00:00'::timestamp, 245),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-02 10:00:00'::timestamp, 49),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-03 10:00:00'::timestamp, 44),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-02-04 10:00:00'::timestamp, 295),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-02-05 10:00:00'::timestamp, 54),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-02-06 10:00:00'::timestamp, 49)
) AS data(id, leg_exercise_type, started_on, count);