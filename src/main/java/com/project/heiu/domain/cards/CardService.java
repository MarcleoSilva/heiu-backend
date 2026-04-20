package com.project.heiu.domain.cards;

import com.project.heiu.domain.cards.dto.CardRequest;
import com.project.heiu.domain.cards.dto.CardResponse;
import com.project.heiu.domain.groups.Group;
import com.project.heiu.domain.groups.GroupRepository;
import com.project.heiu.domain.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    // post
    public void createCard(UUID userId, CardRequest cardRequest, UUID groupId){
        if (cardRepository.existsByTitleAndUserId(cardRequest.title(), userId)){
            throw new RuntimeException("Card already exists");
        }

        Group group = groupRepository.findByIdAndUserId(groupId, userId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Card card = new Card();

        card.setGroup(group);
        updateCardFields(card, cardRequest);

        cardRepository.save(card);
    }

    // get all
    public List<CardResponse> listCards(UUID userId){
        if (!userRepository.existsById(userId)){
            throw new RuntimeException("User not found");
        }

        return cardRepository.findAllByUserId(userId)
                .stream()
                .map(card -> new CardResponse(
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
                ))
                .toList();
    }

    // get by group
    public List<CardResponse> listCardOfGroup(UUID userId, Group group){
        return cardRepository.findAllByGroupIdAndUserId(group.getId(), userId)
                .stream()
                .map(card -> new CardResponse(
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
                ))
                .toList();
    }

    // get one
    public CardResponse getCard(UUID userId, UUID cardId){

        Card card = cardRepository.findByIdAndUserId(cardId, userId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

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

    // update
    public void editCard(UUID userId, UUID cardId, CardRequest cardRequest) {
        if (!userRepository.existsById(userId)){
            throw new RuntimeException("User not found");
        }

        Card card = cardRepository.findByIdAndUserId(cardId, userId)
                .orElseThrow(()-> new RuntimeException("Card not found"));

        updateCardFields(card, cardRequest);

        cardRepository.save(card);

    }

    private void updateCardFields(Card card, CardRequest request) {
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
    }
}
