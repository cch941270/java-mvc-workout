WITH first_user AS (
    SELECT id FROM workout_users WHERE email = 'user1@example.com' LIMIT 1
)
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT first_user.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM first_user,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-17 10:00:00'::timestamp, 10),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-18 10:00:00'::timestamp, 12),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-19 10:00:00'::timestamp, 15),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-20 10:00:00'::timestamp, 8),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-21 10:00:00'::timestamp, 10),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-22 10:00:00'::timestamp, 50),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-23 10:00:00'::timestamp, 80)
) AS data(id, leg_exercise_type, started_on, count);

WITH second_user AS (
    SELECT id FROM workout_users WHERE email = 'user2@example.com' LIMIT 1
)
INSERT INTO leg_exercises (workout_user_id, id, leg_exercise_type, started_on, count)
SELECT second_user.id, data.id, data.leg_exercise_type, data.started_on, data.count
FROM second_user,
(
    VALUES
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-24 10:00:00'::timestamp, 15),
    (nextval('leg_exercises_id_seq'), 'SQUAT', '2026-01-25 10:00:00'::timestamp, 20),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-26 10:00:00'::timestamp, 12),
    (nextval('leg_exercises_id_seq'), 'LUNGE', '2026-01-27 10:00:00'::timestamp, 15),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-28 10:00:00'::timestamp, 100),
    (nextval('leg_exercises_id_seq'), 'STEP_UP', '2026-01-29 10:00:00'::timestamp, 120)
) AS data(id, leg_exercise_type, started_on, count);
