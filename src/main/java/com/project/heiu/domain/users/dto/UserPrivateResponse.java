package com.project.heiu.domain.users.dto;

import java.time.LocalDateTime;

public record UserPrivateResponse(
        String name,
        String username,
        LocalDateTime createdAt
){}

