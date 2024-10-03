package com.banco.digital.ms_tarjetas.service.impl;

import com.banco.digital.ms_tarjetas.model.Card;
import com.banco.digital.ms_tarjetas.request.RegisterCardRequest;
import com.banco.digital.ms_tarjetas.response.RegisterCardResponse;
import com.banco.digital.ms_tarjetas.service.CardEventProcessorService;
import com.banco.digital.ms_tarjetas.service.CardGeneratorService;
import com.banco.digital.ms_tarjetas.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CardEventProcessorServiceImpl implements CardEventProcessorService {
    private final Logger logger = LoggerFactory.getLogger(CardEventProcessorServiceImpl.class);
    private final CardService cardService;
    private final CardGeneratorService cardGeneratorService;

    @Autowired
    public CardEventProcessorServiceImpl(CardService cardService, CardGeneratorService cardGeneratorService) {
        this.cardService = cardService;
        this.cardGeneratorService = cardGeneratorService;
    }

    @Override
    public RegisterCardResponse processCardCreation(RegisterCardRequest registerCardRequest) {
        logger.info("Generate credit card...");

        Card card = cardGeneratorService.generateCreditCard(registerCardRequest);
        cardService.save(card);

        return new RegisterCardResponse("Card registered successfully!", HttpStatus.CREATED.value());
    }
}
