CREATE TABLE workout_user_roles (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    workout_user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    assigned_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    
    PRIMARY KEY (workout_user_id, role_id),

    FOREIGN KEY (workout_user_id) REFERENCES workout_users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);