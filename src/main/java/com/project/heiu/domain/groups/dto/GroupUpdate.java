package com.project.heiu.domain.groups.dto;

import java.util.List;

public record GroupUpdate(
    String name,
    String description,
    String color,
    List<String> tags,
    String photo
) {
}
