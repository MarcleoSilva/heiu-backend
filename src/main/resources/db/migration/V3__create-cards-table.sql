CREATE TABLE cards (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    group_id UUID REFERENCES groups(id) ON DELETE CASCADE,
    title VARCHAR(50) NOT NULL,
    main_info VARCHAR(50),
    color VARCHAR,
    tags  VARCHAR[],
    photo VARCHAR,
    occupation VARCHAR,
    description VARCHAR,
    age VARCHAR(3),
    birthday VARCHAR,
    likes VARCHAR[],
    dislikes VARCHAR[],
    family VARCHAR,
    pets VARCHAR,
    created_at TIMESTAMP DEFAULT NOW()
)