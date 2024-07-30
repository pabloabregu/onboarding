package com.banco.digital.ms_personas.service;

import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.request.UserRequest;
import com.banco.digital.ms_personas.response.Response;
import com.banco.digital.ms_personas.response.UserStateValidationResponse;

import java.util.Optional;

public interface UserService extends BaseService<User, Long> {
    Optional<User> findByDni(String dni);

    Response createUser(UserRequest userRequest);

    UserStateValidationResponse validateUserData(UserRequest user);
}
