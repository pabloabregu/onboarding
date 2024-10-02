package com.banco.digital.ms_personas.service;

import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.request.UserAccountRequest;
import com.banco.digital.ms_personas.request.UserRegisterRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${kafka-topic.nuevo-usuario}")
    private String nameKafkaTopic;

    public void sendEvent(String nameKafkaEvent, User user, UserRegisterRequest userRegisterRequest) throws JsonProcessingException {
        UserAccountRequest userAccountRequest = UserAccountRequest.builder()
                .persNum(user.getIdUser())
                .dni(user.getDni())
                .salary(userRegisterRequest.getSalary())
                .event(nameKafkaEvent).build();

        String jsonMessage = new ObjectMapper().writeValueAsString(userAccountRequest);
        kafkaTemplate.send(nameKafkaTopic, jsonMessage);
    }
}
