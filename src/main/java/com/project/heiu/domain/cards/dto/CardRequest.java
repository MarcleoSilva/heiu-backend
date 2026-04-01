package com.project.heiu.domain.cards.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CardRequest(
        @NotNull String title,
        String mainInfo,
        String color,
        List<String> tags,
        String photo,
        String occupation,
        String description,
        String age,
        String birthday,
        List<String> likes,
        List<String> dislikes,
        String family,
        String pets
) {
}
