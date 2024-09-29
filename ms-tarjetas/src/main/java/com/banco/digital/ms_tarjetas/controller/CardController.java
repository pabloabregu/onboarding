package com.banco.digital.ms_tarjetas.controller;

import com.banco.digital.ms_tarjetas.model.Card;
import com.banco.digital.ms_tarjetas.model.CardStatus;
import com.banco.digital.ms_tarjetas.service.CardService;
import com.banco.digital.ms_tarjetas.service.CardStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardService cardService;
    private final CardStatusService cardStatusService;

    @Autowired
    public CardController( CardService cardService, CardStatusService cardStatusService) {
        this.cardService = cardService;
        this.cardStatusService = cardStatusService;
    }

    @GetMapping("/find")
    public ResponseEntity<List<Card>> listCards() {
        return ResponseEntity.ok(cardService.findAll());
    }

    @GetMapping("/findStatus")
    public ResponseEntity<List<CardStatus>> listStatus() {
        return ResponseEntity.ok(cardStatusService.findAll());
    }
}
