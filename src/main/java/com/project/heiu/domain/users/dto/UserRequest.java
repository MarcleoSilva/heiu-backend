package com.project.heiu.domain.users.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank String name,
        @NotBlank String username,
        @NotBlank String passwordHash
) {
}
