CREATE TABLE groups (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    name VARCHAR(50) NOT NULL,
    description VARCHAR,
    color VARCHAR,
    tags  VARCHAR[],
    photo VARCHAR,
    created_at TIMESTAMP DEFAULT NOW()
)