package com.banco.digital.ms_tarjetas.service;

import com.banco.digital.ms_tarjetas.model.Card;
import com.banco.digital.ms_tarjetas.model.CardStatus;
import com.banco.digital.ms_tarjetas.request.UserCardRequest;

public interface CardGeneratorService {
    Card generateCreditCard(UserCardRequest userCardRequest);

    String generateCardNumber();

    Integer generatePin();

    String generateIssueDate();

    String generateExpirationDate();

    CardStatus generateStatus();
}
