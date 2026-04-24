package com.project.heiu.domain.cards;

import com.project.heiu.domain.cards.dto.CardRequest;
import com.project.heiu.domain.cards.dto.CardResponse;
import com.project.heiu.domain.cards.dto.CardUpdate;
import com.project.heiu.domain.groups.Group;
import com.project.heiu.domain.groups.GroupRepository;
import com.project.heiu.domain.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    // post
    public void createCard(UUID userId, CardRequest request, UUID groupId){
        if (cardRepository.existsByTitleAndUserIdAndGroupId(request.title(), userId, groupId)){
            throw new RuntimeException("Card already exists with this name");
        }

        Group group = groupRepository.findByIdAndUserId(groupId, userId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Card card = new Card();

        card.setGroup(group);
        card.setTitle(request.title());
        card.setMainInfo(request.mainInfo());
        card.setContact(request.contact());
        card.setColor(request.color());
        card.setTags(request.tags());
        card.setPhoto(request.photo());
        card.setOccupation(request.occupation());
        card.setDescription(request.description());
        card.setAge(request.age());
        card.setBirthday(request.birthday());
        card.setLikes(request.likes());
        card.setDislikes(request.dislikes());
        card.setFamily(request.family());
        card.setPets(request.pets());

        cardRepository.save(card);
    }

    // get all
    public List<CardResponse> listCards(UUID userId, UUID groupId){
        if (!groupRepository.existsById(groupId)){
            throw new RuntimeException("Group not found");
        }
        if (!userRepository.existsById(userId)){
            throw new RuntimeException("User not found");
        }

        return cardRepository.findAllByGroupId(groupId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // get one
    public CardResponse getCard(UUID userId, UUID cardId, UUID groupId){

        Card card = cardRepository.findByIdAndGroupIdAndUserId(cardId, groupId, userId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        return mapToResponse(card);
    }

    // update
    @Transactional
    public void editCard(UUID userId, UUID cardId, CardUpdate request, UUID groupId) {
        if (!groupRepository.existsById(groupId)){
            throw new RuntimeException("Group not found");
        }
        if (!userRepository.existsById(userId)){
            throw new RuntimeException("User not found");
        }

        Card card = cardRepository.findByIdAndGroupIdAndUserId(cardId, groupId, userId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        if (request.title() != null &&
                cardRepository.existsByTitleAndUserIdAndIdNotAndGroupId(request.title(), userId, cardId, groupId)) {
            throw new RuntimeException("A card with this title already exists");
        }


        if (request.title() != null) card.setTitle(request.title());
        if (request.mainInfo() != null) card.setMainInfo(request.mainInfo());
        if (request.contact() != null) card.setContact(request.contact());
        if (request.color() != null) card.setColor(request.color());
        if (request.tags() != null) card.setTags(request.tags());
        if (request.photo() != null) card.setPhoto(request.photo());
        if (request.occupation() != null) card.setOccupation(request.occupation());
        if (request.description() != null) card.setDescription(request.description());
        if (request.age() != null) card.setAge(request.age());
        if (request.birthday() != null) card.setBirthday(request.birthday());
        if (request.likes() != null) card.setLikes(request.likes());
        if (request.dislikes() != null) card.setDislikes(request.dislikes());
        if (request.family() != null) card.setFamily(request.family());
        if (request.pets() != null) card.setPets(request.pets());

    }
    // delete
    public void delete (UUID userId, UUID groupId, UUID cardId) {
        Card card = cardRepository.findByIdAndGroupIdAndUserId(cardId, groupId, userId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
    }


    private CardResponse mapToResponse(Card card) {
        return new CardResponse(
                card.getId(),
                card.getTitle(),
                card.getMainInfo(),
                card.getContact(),
                card.getColor(),
                card.getTags(),
                card.getPhoto(),
                card.getOccupation(),
                card.getDescription(),
                card.getAge(),
                card.getBirthday(),
                card.getLikes(),
                card.getDislikes(),
                card.getFamily(),
                card.getPets(),
                card.getCreatedAt()
        );
    }
}
