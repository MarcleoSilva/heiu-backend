package com.project.heiu.domain.groups;

import com.project.heiu.domain.cards.Card;
import com.project.heiu.domain.users.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="groups")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private String name;
    private String description;
    private String color;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(name="tags", columnDefinition = "text[]")
    private List<String> tags;
    private String photo;

    @CreationTimestamp
    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "group")
    private List<Card> card;

    private Integer cardAmount;
}
