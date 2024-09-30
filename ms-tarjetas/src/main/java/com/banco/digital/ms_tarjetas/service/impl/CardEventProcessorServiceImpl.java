package com.banco.digital.ms_tarjetas.service.impl;

import com.banco.digital.ms_tarjetas.model.Card;
import com.banco.digital.ms_tarjetas.model.CardStatus;
import com.banco.digital.ms_tarjetas.request.IssueCardRequest;
import com.banco.digital.ms_tarjetas.service.CardEventProcessorService;
import com.banco.digital.ms_tarjetas.service.CardService;
import com.banco.digital.ms_tarjetas.service.CardStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;

@Service
public class CardEventProcessorServiceImpl implements CardEventProcessorService {
    private final Logger logger = LoggerFactory.getLogger(CardEventProcessorServiceImpl.class);
    private final Random random = new Random();

    private final CardService cardService;
    private final CardStatusService cardStatusService;

    public CardEventProcessorServiceImpl(CardService cardService, CardStatusService cardStatusService) {
        this.cardService = cardService;
        this.cardStatusService = cardStatusService;
    }

    @Override
    public Card processCardIssue(IssueCardRequest request) {
        logger.info("User Request : {}", request.toString());

        String generatedCardNumber = generatedCardNumber();
        Integer generatedPin = generatedPin();

        Optional<CardStatus> cardStatus = cardStatusService.findByDetail("Activa");


        Card card = Card.builder()
                .cardNumber(generatedCardNumber)
                .accountNumber(request.getAccountNumber())
                .expirationDate(formatDate(LocalDate.now().plusYears(4)))
                .pin(generatedPin)
                .cardStatus(cardStatus.get())
                .issueDate(formatDate(LocalDate.now()))
                .type("C") // Asumiendo que "C" significa crédito, usa una constante o enum
                .build();

        cardService.save(card);
        logger.info("User Request : {}", card.toString());


        return card;
    }

    private String generatedCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        // Genera un número de tarjeta de 16 dígitos
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);  // Genera un número entre 0 y 9
            cardNumber.append(digit);
        }
        return cardNumber.toString();
    }

    private Integer generatedPin() {
        return random.nextInt(9000) + 1000;
    }

    private String formatDate(LocalDate date) {
        return DateTimeFormatter.ofPattern("MM/yy").format(date);
    }

}
