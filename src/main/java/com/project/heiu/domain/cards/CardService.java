package com.project.heiu.domain.cards;

import com.project.heiu.domain.cards.dto.CardRequest;
import com.project.heiu.domain.groups.Group;
import com.project.heiu.domain.groups.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final GroupRepository groupRepository;

    // post
    public void createCard(UUID userId, CardRequest cardRequest, UUID groupId){
        if (cardRepository.existsByNameAndUserId(cardRequest.title(), userId)){
            throw new RuntimeException("Card already exists");
        }

        Group group = groupRepository.findByIdAndUserId(groupId, userId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Card card = new Card();

        card.setGroup(group);
        card.setTitle(cardRequest.title());
        card.setMainInfo(cardRequest.mainInfo());
        card.setColor(cardRequest.color());
        card.setTags(cardRequest.tags());
        card.setPhoto(cardRequest.photo());
        card.setOccupation(cardRequest.occupation());
        card.setDescription(cardRequest.description());
        card.setAge(cardRequest.age());
        card.setBirthday(cardRequest.birthday());
        card.setLikes(cardRequest.likes());
        card.setDislikes(cardRequest.dislikes());
        card.setFamily(cardRequest.family());
        card.setPets(cardRequest.pets());

        cardRepository.save(card);

    }
}
