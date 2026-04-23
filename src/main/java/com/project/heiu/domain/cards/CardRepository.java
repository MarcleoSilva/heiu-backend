package com.project.heiu.domain.cards;

import com.project.heiu.domain.groups.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
    boolean existsByTitleAndUserIdAndGroupId(String title, UUID userId, UUID groupId);
    List<Card> findAllByUserId(UUID userId);
    Optional<Card> findByIdAndGroupIdAndUserId(UUID cardId, UUID groupId, UUID userId);
    List<Card> findAllByGroupIdAndUserId(UUID groupId, UUID userId);
    List<Card> findAllByGroupId(UUID groupId);
    Optional<Card> findByIdAndUserId(UUID cardId, UUID userId);
    boolean existsByTitleAndUserIdAndIdNotAndGroupId(String title, UUID userId, UUID cardId, UUID groupId);
}
