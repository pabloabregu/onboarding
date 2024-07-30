package com.banco.digital.ms_personas.controller;

import com.banco.digital.ms_personas.request.UserAccountRequest;
import com.banco.digital.ms_personas.response.Response;
import com.banco.digital.ms_personas.service.impl.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/publish")
    public Response sendMessageToKafkaTopic(@RequestBody UserAccountRequest message) {
        kafkaProducerService.sendMessage("topic_name", message);
        return new Response("Test: " + message.toString(), 200);
    }
}