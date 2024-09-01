package com.banco.digital.ms_cuentas.listener;

import com.banco.digital.ms_cuentas.request.UserAccountRequest;
import com.banco.digital.ms_cuentas.service.AccountEventProcessorService;
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

    private final AccountEventProcessorService accountEventProcessorService;

    @Autowired
    public AccountCreationListener(AccountEventProcessorService accountEventProcessorService) {
        this.accountEventProcessorService = accountEventProcessorService;
    }

    @KafkaListener(topics = "${kafka-topic.alta-usuario}", groupId = "${spring.kafka.consumer.group-id}")
    public void accountCreation(String request) throws IOException, URISyntaxException, InterruptedException {
        logger.info("Account creation...");
        UserAccountRequest userAccountRequest = new ObjectMapper().readValue(request, UserAccountRequest.class);
        accountEventProcessorService.processAccountCreation(userAccountRequest);
    }
}
