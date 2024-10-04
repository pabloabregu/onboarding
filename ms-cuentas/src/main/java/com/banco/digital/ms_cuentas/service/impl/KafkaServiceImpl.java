package com.banco.digital.ms_cuentas.service.impl;

import com.banco.digital.ms_cuentas.request.UserCardRequest;
import com.banco.digital.ms_cuentas.service.KafkaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements KafkaService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaServiceImpl.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${kafka-topic.nueva-cuenta}")
    private String nameKafkaTopic;
    @Override
    public void sendEvent(UserCardRequest userCardRequest) throws JsonProcessingException {
        logger.info("Registrando evento de creacion de cuenta...");
        String jsonMessage = new ObjectMapper().writeValueAsString(userCardRequest);
        kafkaTemplate.send(nameKafkaTopic, jsonMessage);
    }
}
