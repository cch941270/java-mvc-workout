ALTER TABLE workout_users ADD COLUMN username varchar(250) UNIQUE;
UPDATE workout_users SET username = email WHERE username IS NULL;
ALTER TABLE workout_users ALTER COLUMN username SET NOT NULL;