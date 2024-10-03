package com.banco.digital.ms_personas.service;

import com.banco.digital.ms_personas.request.UserRegisterRequest;
import com.banco.digital.ms_personas.response.RegisterUserResponse;

public interface OnboardingService {
    RegisterUserResponse registerUser(UserRegisterRequest userRegisterRequest);
}
