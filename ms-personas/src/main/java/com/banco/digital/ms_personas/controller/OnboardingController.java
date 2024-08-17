package com.banco.digital.ms_personas.controller;

import com.banco.digital.ms_personas.request.UserRequest;
import com.banco.digital.ms_personas.response.Response;
import com.banco.digital.ms_personas.service.OnboardingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class OnboardingController {

    @Autowired
    private OnboardingService onboardingService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody UserRequest userRequest) throws JsonProcessingException {
        Response response = onboardingService.register(userRequest);
        return ResponseEntity.ok(response);
    }
}