package com.project.heiu.controller;

import com.project.heiu.domain.cards.CardService;
import com.project.heiu.domain.cards.dto.CardRequest;
import com.project.heiu.domain.cards.dto.CardResponse;
import com.project.heiu.domain.cards.dto.CardUpdate;
import com.project.heiu.domain.users.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("heiu/groups/{groupId}/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<Void> createCard(@AuthenticationPrincipal User user, @RequestBody @Valid CardRequest request, @PathVariable UUID groupId){

        cardService.createCard(user.getId(), request, groupId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CardResponse>> listAllCards(@AuthenticationPrincipal User user, @PathVariable UUID groupId){
        return ResponseEntity.ok(cardService.listCards(user.getId(), groupId));
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<CardResponse> getCard(@AuthenticationPrincipal User user, @PathVariable UUID cardId, @PathVariable UUID groupId ){
        return ResponseEntity.ok(cardService.getCard(user.getId(), cardId, groupId));
    }

    @PatchMapping("/{cardId}")
    public ResponseEntity<Void> updateCard(@AuthenticationPrincipal User user, @PathVariable UUID cardId, @RequestBody @Valid CardUpdate request, @PathVariable UUID groupId){

        cardService.editCard(user.getId(), cardId, request, groupId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
