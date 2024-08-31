package com.banco.digital.ms_personas.service;

import com.banco.digital.ms_personas.request.UserRegisterRequest;
import com.banco.digital.ms_personas.response.Response;

public interface OnboardingService {
    Response register(UserRegisterRequest userRegisterRequest);
}
