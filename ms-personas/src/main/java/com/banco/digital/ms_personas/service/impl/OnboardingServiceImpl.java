package com.banco.digital.ms_personas.service.impl;

import com.banco.digital.ms_personas.model.Address;
import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.request.UserRegisterRequest;
import com.banco.digital.ms_personas.response.Response;
import com.banco.digital.ms_personas.service.AddressService;
import com.banco.digital.ms_personas.service.KafkaService;
import com.banco.digital.ms_personas.service.OnboardingService;
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
public class OnboardingServiceImpl implements OnboardingService {
    private static final Logger logger = LoggerFactory.getLogger(OnboardingServiceImpl.class);

    private final UserService userService;
    private final AddressService addressService;
    private final KafkaService kafkaService;

    @Autowired
    public OnboardingServiceImpl(UserService userService, AddressService addressService, KafkaService kafkaService) {
        this.userService = userService;
        this.addressService = addressService;
        this.kafkaService = kafkaService;
    }

    @Override
    public Response register(UserRegisterRequest userRegisterRequest) {
        try {
            Optional<User> user = userService.findByDni(userRegisterRequest.getDni());

            if (user.isEmpty())
                return handleRegisterNewCustomer(userRegisterRequest);

            String userState = user.get().getState().getDescription();
            return switch (userState) {
                case State.ACTIVO -> handleAlreadyActiveCustomer();
                case State.INACTIVO -> handleReactivateCustomer(userRegisterRequest);
                case State.BLOQUEADO -> handleBlockedCustomer();
                default -> throw new IllegalStateException("Unexpected value: " + userState);
            };
        } catch (JsonProcessingException ex) {
            logger.error("JSON Processing Error: {}", ex.getMessage());
            return new Response("Error : JSON Processing", HttpStatus.PROCESSING.value());
        }
    }

    private Response handleRegisterNewCustomer(UserRegisterRequest userRegisterRequest) throws JsonProcessingException {
        User user = userService.generateUser(userRegisterRequest);
        Address address = addressService.generateAddress(userRegisterRequest, user);

        logger.trace("NEW USER : {}", user);
        logger.trace("NEW ADDRESS : {}", address);

        kafkaService.sendEvent(KafkaEvents.CREATE_USER, user, userRegisterRequest);
        return new Response("User created!", HttpStatus.CREATED.value());
    }

    private Response handleAlreadyActiveCustomer() {
        return new Response("There is an active user with the same DNI.", HttpStatus.CONFLICT.value());
    }

    private Response handleReactivateCustomer(UserRegisterRequest userRegisterRequest) {
        userService.changeStateFromUser(userRegisterRequest, State.ACTIVO);
        return new Response("User ACTIVE!", HttpStatus.CONFLICT.value());
    }

    private Response handleBlockedCustomer() {
        return new Response("The user is blocked.", HttpStatus.FORBIDDEN.value());
    }


}
