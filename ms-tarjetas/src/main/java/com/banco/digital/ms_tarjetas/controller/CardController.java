package com.banco.digital.ms_tarjetas.controller;

import com.banco.digital.ms_tarjetas.model.Card;
import com.banco.digital.ms_tarjetas.request.IssueCardRequest;
import com.banco.digital.ms_tarjetas.service.CardEventProcessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardEventProcessorService cardEventProcessorService;

    @Autowired
    public CardController(CardEventProcessorService cardEventProcessorService) {
        this.cardEventProcessorService = cardEventProcessorService;
    }

    @PostMapping("/issue")
    public ResponseEntity<String> issueCard(@Valid @RequestBody IssueCardRequest request) {
        try {
            Card card = cardEventProcessorService.processCardIssue(request);
            return new ResponseEntity<>("Card issued successfully! " + card.toString(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while issuing the card", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
