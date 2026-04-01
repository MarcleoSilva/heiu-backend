package com.project.heiu.domain.users.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
        @NotBlank String name,
        @NotBlank String username,
        @NotBlank String passwordHash
) {
}
