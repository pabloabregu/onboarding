package com.banco.digital.ms_personas.service;

import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.request.UserRegisterRequest;

import java.util.Optional;

public interface UserService extends BaseService<User, Integer> {
    Optional<User> findByDni(String dni);

    User generateUser(UserRegisterRequest userRegisterRequest);

    void activateUser(UserRegisterRequest userRegisterRequest);
}
