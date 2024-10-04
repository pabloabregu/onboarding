package com.banco.digital.ms_personas.service.impl;

import com.banco.digital.ms_personas.model.Address;
import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.request.UserRegisterRequest;
import com.banco.digital.ms_personas.response.RegisterUserResponse;
import com.banco.digital.ms_personas.service.AddressService;
import com.banco.digital.ms_personas.service.KafkaService;
import com.banco.digital.ms_personas.service.UserOnboardingService;
import com.banco.digital.ms_personas.service.UserService;
import com.banco.digital.ms_personas.util.KafkaEvents;
import com.banco.digital.ms_personas.util.State;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserOnboardingServiceImpl implements UserOnboardingService {
    private static final Logger logger = LoggerFactory.getLogger(UserOnboardingServiceImpl.class);
    private final UserService userService;
    private final AddressService addressService;
    private final KafkaService kafkaService;

    @Autowired
    public UserOnboardingServiceImpl(UserService userService, AddressService addressService, KafkaService kafkaService) {
        this.userService = userService;
        this.addressService = addressService;
        this.kafkaService = kafkaService;
    }

    @Override
    public RegisterUserResponse registerUser(UserRegisterRequest userRegisterRequest) {
        Optional<User> userOptional = userService.findByDni(userRegisterRequest.getDni());

        if (userOptional.isEmpty())
            return handleRegisterNewCustomer(userRegisterRequest);

        else {
            User user = userOptional.get();
            String userState = user.getState().getDescription();
            logger.info("Estado de usuario : {}", userState);
            return switch (userState) {
                case State.ACTIVO -> handleAlreadyActiveCustomer(userRegisterRequest);
                case State.INACTIVO -> handleReactivateCustomer(userRegisterRequest);
                case State.BLOQUEADO -> handleBlockedCustomer(userRegisterRequest);
                default -> throw new IllegalStateException("Unexpected value: " + userState);
            };
        }
    }

    private RegisterUserResponse handleRegisterNewCustomer(UserRegisterRequest userRegisterRequest) {
        logger.info("Generar nuevo usuario...");

        try {
            User user = userService.generateUser(userRegisterRequest);
            Address address = addressService.generateAddress(userRegisterRequest, user);

            if (user == null || address == null) {
                return new RegisterUserResponse("Error creating user or address.", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }

            sendCreateUserEvent(user, userRegisterRequest);

            return new RegisterUserResponse("User registered successfully!", HttpStatus.CREATED.value());
        } catch (JsonProcessingException e) {
            logger.error("Error kafka para nuevo usuario: {}", e.getMessage());
            return new RegisterUserResponse("Error sending event!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private void sendCreateUserEvent(User user, UserRegisterRequest userRegisterRequest) throws JsonProcessingException {
        kafkaService.sendEvent(KafkaEvents.CREATE_USER, user, userRegisterRequest);
    }

    private RegisterUserResponse handleAlreadyActiveCustomer(UserRegisterRequest userRegisterRequest) {
        logger.info("Usuario ya registrado con el DNI : {}", userRegisterRequest.getDni());
        return new RegisterUserResponse("There is an active user with the same DNI!", HttpStatus.CONFLICT.value());
    }

    private RegisterUserResponse handleReactivateCustomer(UserRegisterRequest userRegisterRequest) {
        logger.info("Usuario inactivo con DNI : {}", userRegisterRequest.getDni());
        userService.activateUser(userRegisterRequest);
        return new RegisterUserResponse("User reactivated!", HttpStatus.CONFLICT.value());
    }

    private RegisterUserResponse handleBlockedCustomer(UserRegisterRequest userRegisterRequest) {
        logger.info("Usuario con el DNI {} está bloqueado", userRegisterRequest.getDni());
        return new RegisterUserResponse("The user is blocked!", HttpStatus.FORBIDDEN.value());
    }
}
