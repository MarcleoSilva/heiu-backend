package com.project.heiu.domain.users.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record UserPrivateResponseDTO (
        String name,
        String username,
        LocalDateTime createdAt
){}

