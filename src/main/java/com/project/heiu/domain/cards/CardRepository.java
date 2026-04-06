package com.project.heiu.domain.cards;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    boolean existsByNameAndUserId(String title, UUID userId);
}
