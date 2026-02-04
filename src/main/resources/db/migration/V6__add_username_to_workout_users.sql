ALTER TABLE workout_users ADD COLUMN username varchar(250) UNIQUE;
UPDATE workout_users SET username = 'user1' WHERE email = 'user1@example.com';
UPDATE workout_users SET username = 'user2' WHERE email = 'user2@example.com';
ALTER TABLE workout_users ALTER COLUMN username SET NOT NULL;