package com.project.heiu.domain.cards.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CardResponse(
        UUID id,
        String title,
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
        String pets,
        LocalDateTime createdAt
) {
}
