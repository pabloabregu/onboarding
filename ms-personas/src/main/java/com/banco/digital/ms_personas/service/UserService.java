package com.banco.digital.ms_personas.service;

import com.banco.digital.ms_personas.enums.State;
import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.request.UserRequest;

import java.util.Optional;

public interface UserService extends BaseService<User, Long> {
    Optional<User> findByDni(String dni);
    State validateUser(UserRequest userRequest);
    User generateUser(UserRequest userRequest);
    void changeStateFromUser(UserRequest userRequest, String activo);
}
