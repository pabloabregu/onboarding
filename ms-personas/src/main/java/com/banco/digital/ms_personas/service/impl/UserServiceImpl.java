package com.banco.digital.ms_personas.service.impl;

import com.banco.digital.ms_personas.enums.UserStateEnum;
import com.banco.digital.ms_personas.enums.UserTypeEnum;
import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.model.UserState;
import com.banco.digital.ms_personas.model.UserType;
import com.banco.digital.ms_personas.repository.UserRepository;
import com.banco.digital.ms_personas.request.UserRegisterRequest;
import com.banco.digital.ms_personas.service.UserService;
import com.banco.digital.ms_personas.service.UserStateService;
import com.banco.digital.ms_personas.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserStateService userStateService;

    private final UserTypeService userTypeService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserStateService userStateService, UserTypeService userTypeService) {
        this.userRepository = userRepository;
        this.userStateService = userStateService;
        this.userTypeService = userTypeService;
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
    public User generateUser(UserRegisterRequest userRegisterRequest) {
        UserState userState = userStateService.findByDescription(UserStateEnum.ACTIVO.name());
        UserType userType = userTypeService.findByDescription(UserTypeEnum.CLIENTE.name());
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
    public void activateUser(UserRegisterRequest userRegisterRequest) {
        UserState userState = userStateService.findByDescription(UserStateEnum.ACTIVO.getType());

        Optional<User> user = userRepository.findByDni(userRegisterRequest.getDni());
        user.ifPresent(value -> value.setState(userState));
    }
}