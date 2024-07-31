package com.banco.digital.ms_personas.service;

import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.request.UserRequest;
import com.banco.digital.ms_personas.response.Response;
import com.banco.digital.ms_personas.response.UserStateValidationResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Optional;

public interface UserService extends BaseService<User, Long> {
    Optional<User> findByDni(String dni);

    Response createUser(UserRequest userRequest) throws JsonProcessingException;

    UserStateValidationResponse validateUserData(UserRequest user);
}
