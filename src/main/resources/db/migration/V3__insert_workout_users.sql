INSERT INTO workout_users (id, email, hashed_password)
VALUES
    (nextval('workout_users_id_seq'), 'user1@example.com', '$2a$12$OIES19OQ9AuwYKNF5tckneMCtkOuSLm2U84uZ2RlJOY1rPvB6m2DS'),
    (nextval('workout_users_id_seq'), 'user2@example.com', '$2a$12$oPirXmFPZuEUyf.FBsgivOzcPSvV5CwB9rvDZ89v9EgLsQf0/C6WW');
