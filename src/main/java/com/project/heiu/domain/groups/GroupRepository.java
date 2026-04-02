package com.project.heiu.domain.groups;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
    boolean existsByNameAndUserId(String name, UUID userId);
    boolean existsById(UUID id);
    List<Group> findAllByUserId(UUID userId);
    Optional<Group> findByIdAndUserId(UUID id, UUID userId);
    Optional<Group> findByNameAndUserId(String name, UUID userID);

}
