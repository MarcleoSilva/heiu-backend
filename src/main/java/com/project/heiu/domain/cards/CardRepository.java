package com.project.heiu.domain.cards;

import com.project.heiu.domain.groups.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    boolean existsByTitleAndUserId(String title, UUID userId);
    List<Card> findAllByUserId(UUID userId);
    List<Card> findAllByGroupAndUserId(UUID groupId, UUID userId);
    Card findByIdAndUserId(UUID cardId, UUID userId);
}
