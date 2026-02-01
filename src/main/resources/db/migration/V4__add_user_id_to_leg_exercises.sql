ALTER TABLE leg_exercises ADD COLUMN workout_user_id BIGINT NOT NULL;
UPDATE leg_exercises SET workout_user_id = 1 WHERE workout_user_id IS NULL;
