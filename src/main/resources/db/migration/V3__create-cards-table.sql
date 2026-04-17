CREATE TABLE cards (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    group_id UUID REFERENCES groups(id) ON DELETE CASCADE,
    title VARCHAR(50) NOT NULL,
    main_info VARCHAR(50),
    color VARCHAR,
    tags  VARCHAR[],
    photo VARCHAR,
    occupation VARCHAR,
    description VARCHAR,
    age INTEGER,
    birthday VARCHAR,
    likes VARCHAR[],
    dislikes VARCHAR[],
    family VARCHAR,
    pets VARCHAR,
    created_at TIMESTAMP DEFAULT NOW()
)