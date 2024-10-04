package com.banco.digital.ms_cuentas.listener;

import com.banco.digital.ms_cuentas.request.UserAccountRequest;
import com.banco.digital.ms_cuentas.service.AccountProcessorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;
import java.net.URISyntaxException;

@Configuration
public class AccountCreationListener {
    private final Logger logger = LoggerFactory.getLogger(AccountCreationListener.class);

    private final AccountProcessorService accountProcessorService;

    @Autowired
    public AccountCreationListener(AccountProcessorService accountProcessorService) {
        this.accountProcessorService = accountProcessorService;
    }

    @KafkaListener(topics = "${kafka-topic.nuevo-usuario}", groupId = "${spring.kafka.consumer.group-id}")
    public void accountCreation(String request) throws IOException, URISyntaxException, InterruptedException {
        logger.info("Listener: creacion de cuentas...");
        UserAccountRequest userAccountRequest = new ObjectMapper().readValue(request, UserAccountRequest.class);
        accountProcessorService.processAccountCreation(userAccountRequest);
    }
}
