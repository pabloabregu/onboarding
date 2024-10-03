package com.banco.digital.ms_tarjetas.service.impl;

import com.banco.digital.ms_tarjetas.model.Card;
import com.banco.digital.ms_tarjetas.request.RegisterCardRequest;
import com.banco.digital.ms_tarjetas.response.RegisterCardResponse;
import com.banco.digital.ms_tarjetas.service.CardEventProcessorService;
import com.banco.digital.ms_tarjetas.service.CardGeneratorService;
import com.banco.digital.ms_tarjetas.service.CardService;
import com.banco.digital.ms_tarjetas.service.CardStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CardEventProcessorServiceImpl implements CardEventProcessorService {
    private final CardService cardService;
    private final CardStatusService cardStatusService;
    private final CardGeneratorService cardGeneratorService;

    @Autowired
    public CardEventProcessorServiceImpl(CardService cardService, CardStatusService cardStatusService, CardGeneratorService cardGeneratorService) {
        this.cardService = cardService;
        this.cardStatusService = cardStatusService;
        this.cardGeneratorService = cardGeneratorService;
    }

    @Override
    public RegisterCardResponse processCardCreation(RegisterCardRequest request) {
        String cardNumber = cardGeneratorService.generateCardNumber();
        String pin = cardGeneratorService.generatePin();
        String issueDate = cardGeneratorService.generateIssueDate();
        String expirationDate = cardGeneratorService.generateExpirationDate();

        Card card = Card.builder()
                .cardNumber(cardNumber)
                .accountNumber(request.getAccountNumber())
                .expirationDate(expirationDate)
                .pin(Integer.parseInt(pin))
                .cardStatus(cardStatusService.findByDetail("Activa").get())
                .issueDate(issueDate)
                .type("C")
                .build();

        cardService.save(card);

        return new RegisterCardResponse("Tarjeta registrada exitosamente", HttpStatus.CREATED.value());
    }
}
