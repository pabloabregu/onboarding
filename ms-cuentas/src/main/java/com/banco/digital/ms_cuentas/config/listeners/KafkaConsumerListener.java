package com.banco.digital.ms_cuentas.config.listeners;

import com.banco.digital.ms_cuentas.request.UserAccountRequest;
import com.banco.digital.ms_cuentas.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumerListener {
    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    private AccountService accountService;

    @KafkaListener(topics = "topic_name", groupId = "cuentas")
    public void listener(UserAccountRequest request) {
        System.out.println("Test Listener");
        System.out.println("Received: " + request.toString());
    }
}
