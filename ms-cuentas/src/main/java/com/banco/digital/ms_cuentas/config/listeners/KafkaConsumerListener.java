package com.banco.digital.ms_cuentas.config.listeners;

import com.banco.digital.ms_cuentas.request.UserAccountRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

@Configuration
public class KafkaConsumerListener {
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @KafkaListener(topics = "alta-usuario", groupId = "cuentas")
    public void listener(String request) throws IOException {
        UserAccountRequest userAccountRequest = new ObjectMapper().readValue(request, UserAccountRequest.class);

        logger.info("User ID : {}, Salary: {}", userAccountRequest.getPersNum(), userAccountRequest.getSalary());
    }
}
