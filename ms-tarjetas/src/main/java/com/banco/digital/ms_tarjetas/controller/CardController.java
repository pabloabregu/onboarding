package com.banco.digital.ms_tarjetas.controller;

import com.banco.digital.ms_tarjetas.request.RegisterCardRequest;
import com.banco.digital.ms_tarjetas.response.RegisterCardResponse;
import com.banco.digital.ms_tarjetas.service.CardEventProcessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/register")
    public ResponseEntity<RegisterCardResponse> registerCard(@Valid @RequestBody RegisterCardRequest request) {
        RegisterCardResponse response = cardEventProcessorService.processCardCreation(request);
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }
}
