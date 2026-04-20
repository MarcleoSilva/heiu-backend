package com.project.heiu.domain.cards;

import com.project.heiu.domain.groups.Group;
import com.project.heiu.domain.users.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name="cards")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    private String title;
    @Column(name="main_info")
    private String mainInfo;
    private String contact;
    private String color;
    private List<String> tags;
    private String photo;
    private String occupation;
    private String description;
    private Integer age;
    private String birthday;
    private List<String> likes;
    private List<String> dislikes;
    private String family;
    private String pets;

    @CreationTimestamp
    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

}
