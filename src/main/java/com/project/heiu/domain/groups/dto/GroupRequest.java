package com.project.heiu.domain.groups.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record GroupRequest(
        @NotNull String name,
        String description,
        String color,
        List<String> tags,
        String photo

) {
}
