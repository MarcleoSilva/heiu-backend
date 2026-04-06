package com.project.heiu.domain.cards.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record CardRequest(
        @NotNull String title,
        UUID groupId,
        String mainInfo,
        String color,
        List<String> tags,
        String photo,
        String occupation,
        String description,
        Integer age,
        String birthday,
        List<String> likes,
        List<String> dislikes,
        String family,
        String pets
) {
}
