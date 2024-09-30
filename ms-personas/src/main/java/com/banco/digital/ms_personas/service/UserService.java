package com.banco.digital.ms_personas.service;

import com.banco.digital.ms_personas.enums.State;
import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.request.UserRegisterRequest;

import java.util.Optional;

public interface UserService extends BaseService<User, Long> {
    Optional<User> findByDni(String dni);
    User generateUser(UserRegisterRequest userRegisterRequest);

    void changeStateFromUser(UserRegisterRequest userRegisterRequest, String activo);
}
