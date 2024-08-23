package com.banco.digital.ms_personas.service;

import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.request.UserAccountRequest;
import com.banco.digital.ms_personas.request.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka-topic.alta-usuario}")
    private String nameKafkaTopic;

    public void sendEvent(String nameKafkaEvent, User user, UserRequest userRequest) throws JsonProcessingException {
        UserAccountRequest userAccountRequest = UserAccountRequest.builder()
                .persNum(user.getIdUser())
                .dni(user.getDni())
                .salary(userRequest.getSalary())
                .event(nameKafkaEvent).build();

        String jsonMessage = new ObjectMapper().writeValueAsString(userAccountRequest);
        kafkaTemplate.send(nameKafkaTopic, jsonMessage);
    }
}
