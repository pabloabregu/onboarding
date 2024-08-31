package com.banco.digital.ms_personas.service.impl;

import com.banco.digital.ms_personas.enums.State;
import com.banco.digital.ms_personas.enums.Type;
import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.model.UserState;
import com.banco.digital.ms_personas.model.UserType;
import com.banco.digital.ms_personas.repository.UserRepository;
import com.banco.digital.ms_personas.repository.UserStateRepository;
import com.banco.digital.ms_personas.repository.UserTypeRepository;
import com.banco.digital.ms_personas.request.UserRegisterRequest;
import com.banco.digital.ms_personas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserStateRepository userStateRepository;

    private final UserTypeRepository userTypeRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserStateRepository userStateRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userStateRepository = userStateRepository;
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByDni(String dni) {
        return userRepository.findByDni(dni);
    }

    @Override
    public State validateUser(UserRegisterRequest userRegisterRequest) {
        return findByDni(userRegisterRequest.getDni())
                .map(user -> switch (user.getState().getDescription().toUpperCase()) {
                    case "ACTIVO" -> State.ACTIVO;
                    case "INACTIVO" -> State.INACTIVO;
                    case "BLOQUEADO" -> State.BLOQUEADO;
                    default -> State.NO_EXISTE;
                })
                .orElse(State.NO_EXISTE);
    }

    @Override
    public User generateUser(UserRegisterRequest userRegisterRequest) {
        UserState userState = userStateRepository.findByDescription(State.ACTIVO.name());
        UserType userType = userTypeRepository.findByDescription(Type.CLIENTE.name());
        User user = User.builder()
                .firstname(userRegisterRequest.getFirstname())
                .lastname(userRegisterRequest.getLastname())
                .dni(userRegisterRequest.getDni())
                .state(userState)
                .type(userType)
                .build();
        userRepository.save(user);
        return user;
    }

    @Override
    public void changeStateFromUser(UserRegisterRequest userRegisterRequest, String activo) {

    }
}