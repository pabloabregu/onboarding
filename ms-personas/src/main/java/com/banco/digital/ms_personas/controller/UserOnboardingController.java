package com.banco.digital.ms_personas.controller;

import com.banco.digital.ms_personas.request.UserRegisterRequest;
import com.banco.digital.ms_personas.response.RegisterUserResponse;
import com.banco.digital.ms_personas.service.UserOnboardingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserOnboardingController {

    private final UserOnboardingService userOnboardingService;

    @Autowired
    public UserOnboardingController(UserOnboardingService userOnboardingService) {
        this.userOnboardingService = userOnboardingService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        RegisterUserResponse response = userOnboardingService.registerUser(userRegisterRequest);
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }
}
