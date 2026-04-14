package com.project.heiu.domain.users;

import com.project.heiu.domain.groups.Group;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 200)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "created_id", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private Set<Group> group;
}
