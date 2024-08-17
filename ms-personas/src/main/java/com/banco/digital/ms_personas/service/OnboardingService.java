package com.banco.digital.ms_personas.service;

import com.banco.digital.ms_personas.request.UserRequest;
import com.banco.digital.ms_personas.response.Response;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OnboardingService {
    Response register(UserRequest userRequest) throws JsonProcessingException;
}
