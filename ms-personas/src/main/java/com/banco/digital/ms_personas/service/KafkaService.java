package com.banco.digital.ms_personas.service;

import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.request.UserAccountRequest;
import com.banco.digital.ms_personas.request.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent(String nameKafkaTopic, User user, UserRequest userRequest) throws JsonProcessingException {
        String eventNewUser = "new-user";
        UserAccountRequest userAccountRequest = UserAccountRequest.builder()
                .persNum(user.getIdUser())
                .dni(user.getDni())
                .salary(userRequest.getSalary())
                .event(eventNewUser).build();

        String jsonMessage = new ObjectMapper().writeValueAsString(userAccountRequest);
        kafkaTemplate.send(nameKafkaTopic, jsonMessage);
    }
}
