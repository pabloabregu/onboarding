package com.banco.digital.ms_tarjetas.listener;

import com.banco.digital.ms_tarjetas.request.RegisterCardRequest;
import com.banco.digital.ms_tarjetas.service.CardEventProcessorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class CardCreationListener {
    private final Logger logger = LoggerFactory.getLogger(CardCreationListener.class);

    private final CardEventProcessorService cardEventProcessorService;

    @Autowired
    public CardCreationListener(CardEventProcessorService cardEventProcessorService) {
        this.cardEventProcessorService = cardEventProcessorService;
    }

    @KafkaListener(topics = "${kafka-topic.nueva-cuenta}", groupId = "${spring.kafka.consumer.group-id}")
    public void cardCreation(String request) throws JsonProcessingException {
        logger.info("Listener: card creation...");
        RegisterCardRequest registerCardRequest = new ObjectMapper().readValue(request, RegisterCardRequest.class);
        cardEventProcessorService.processCardCreation(registerCardRequest);
    }
}
