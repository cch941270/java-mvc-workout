WITH
    first_user AS (
        SELECT id FROM workout_users WHERE email = 'user1@example.com' LIMIT 1
    ),
    user_role AS (
        SELECT id FROM roles WHERE name = 'USER' LIMIT 1
    )
INSERT INTO workout_user_roles (workout_user_id, role_id)
SELECT first_user.id, user_role.id
FROM first_user, user_role;

WITH
    second_user AS (
        SELECT id FROM workout_users WHERE email = 'user2@example.com' LIMIT 1
    ),
    user_role AS (
        SELECT id FROM roles WHERE name = 'USER' LIMIT 1
    )
INSERT INTO workout_user_roles (workout_user_id, role_id)
SELECT second_user.id, user_role.id
FROM second_user, user_role;

WITH
    admin_user AS (
        SELECT id FROM workout_users WHERE email = 'admin@workout.com' LIMIT 1
    ),
    user_role AS (
        SELECT id FROM roles WHERE name = 'ADMIN' LIMIT 1
    )
INSERT INTO workout_user_roles (workout_user_id, role_id)
SELECT admin_user.id, user_role.id
FROM admin_user, user_role;