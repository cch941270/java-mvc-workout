INSERT INTO workout_users (id, email, hashed_password)
VALUES
    (nextval('workout_users_id_seq'), 'user1@example.com', 'password1'),
    (nextval('workout_users_id_seq'), 'user2@example.com', 'password2');
