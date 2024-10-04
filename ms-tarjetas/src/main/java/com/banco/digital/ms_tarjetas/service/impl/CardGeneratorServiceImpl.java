package com.banco.digital.ms_tarjetas.service.impl;

import com.banco.digital.ms_tarjetas.enums.CardStatusEnum;
import com.banco.digital.ms_tarjetas.enums.TypeCardEnum;
import com.banco.digital.ms_tarjetas.model.Card;
import com.banco.digital.ms_tarjetas.model.CardStatus;
import com.banco.digital.ms_tarjetas.request.UserCardRequest;
import com.banco.digital.ms_tarjetas.service.CardGeneratorService;
import com.banco.digital.ms_tarjetas.service.CardService;
import com.banco.digital.ms_tarjetas.service.CardStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class CardGeneratorServiceImpl implements CardGeneratorService {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/yy");
    private final Random random = new Random();
    private final CardStatusService cardStatusService;

    @Autowired
    public CardGeneratorServiceImpl(CardStatusService cardStatusService) {
        this.cardStatusService = cardStatusService;
    }

    @Override
    public Card generateCreditCard(UserCardRequest userCardRequest) {
        return Card.builder()
                .cardNumber(generateCardNumber())
                .accountNumber(userCardRequest.getAccountNumber())
                .expirationDate(generateExpirationDate())
                .pin(generatePin())
                .cardStatus(generateStatus())
                .issueDate(generateIssueDate())
                .type(TypeCardEnum.CREDIT.getType()).build();
    }

    @Override
    public String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            cardNumber.append(digit);
        }
        return cardNumber.toString();
    }

    @Override
    public Integer generatePin() {
        return random.nextInt(9000) + 1000;
    }

    @Override
    public String generateIssueDate() {
        LocalDate issueDate = LocalDate.now();
        return issueDate.format(DATE_FORMATTER);
    }

    @Override
    public String generateExpirationDate() {
        LocalDate expirationDate = LocalDate.now().plusYears(4);
        return expirationDate.format(DATE_FORMATTER);
    }

    @Override
    public CardStatus generateStatus() {
        return cardStatusService.findByDetail(CardStatusEnum.ACTIVE.getType())
                .orElse(new CardStatus());
    }
}
