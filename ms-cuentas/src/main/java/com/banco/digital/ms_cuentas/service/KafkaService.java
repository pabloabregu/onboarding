package com.banco.digital.ms_cuentas.service;

import com.banco.digital.ms_cuentas.request.UserAccountEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;
import java.net.URISyntaxException;

@Configuration
public class KafkaService {
    private final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private UserEventListenerService userEventListenerService;

    @KafkaListener(topics = "${kafka-topic.alta-usuario}", groupId = "${spring.kafka.consumer.group-id}")
    public void eventListeners(String request) throws IOException, URISyntaxException, InterruptedException {
        UserAccountEvent userAccountRequest = new ObjectMapper().readValue(request, UserAccountEvent.class);

        logger.trace("User Request : {}", userAccountRequest);

        userEventListenerService.processClientEvent(userAccountRequest);
    }
}
