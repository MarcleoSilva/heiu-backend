package com.project.heiu.domain.groups.dto;

import com.project.heiu.domain.cards.Card;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record GroupResponse(
        UUID id,
        String name,
        String description,
        String color,
        List<String> tags,
        String photo,
        LocalDateTime createdAt,
        Integer cardAmount

) {
}
