WITH
    new_users AS (
        INSERT INTO workout_users (id, email, username, password)
        VALUES
            (nextval('workout_users_id_seq'), 'alexander@example.com', 'alexander', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'benjamin@example.com', 'benjamin', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'christopher@example.com', 'christopher', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'daniel@example.com', 'daniel', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'ethan@example.com', 'ethan', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'felix@example.com', 'felix', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'gabriel@example.com', 'gabriel', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'henry@example.com', 'henry', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'isaac@example.com', 'isaac', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'jacob@example.com', 'jacob', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'kevin@example.com', 'kevin', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'liam@example.com', 'liam', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'mason@example.com', 'mason', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'nathan@example.com', 'nathan', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'oliver@example.com', 'oliver', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'patrick@example.com', 'patrick', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'quinn@example.com', 'quinn', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'ryan@example.com', 'ryan', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'samuel@example.com', 'samuel', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm'),
            (nextval('workout_users_id_seq'), 'thomas@example.com', 'thomas', '$2a$12$dqVlfGA80ih/S0DqhsCW0OBa/mAnUZI.YfT8caSphOKo.soThnVLm')
        RETURNING id
    ),
    user_role AS (
        SELECT id FROM roles WHERE name = 'USER' LIMIT 1
    )
INSERT INTO workout_user_roles (workout_user_id, role_id)
SELECT new_users.id, user_role.id
FROM new_users, user_role;