package com.banco.digital.ms_personas.service.impl;

import com.banco.digital.ms_personas.request.UserAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, UserAccountRequest> kafkaTemplate;


    public void KafkaProducerService(KafkaTemplate<String, UserAccountRequest> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, UserAccountRequest message) {
        kafkaTemplate.send(topic, message);
    }
}
