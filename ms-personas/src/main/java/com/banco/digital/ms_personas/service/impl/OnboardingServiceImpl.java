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
        if (userRegisterRequest == null || userRegisterRequest.getDni() == null) {
            return new Response("Invalid request data.", HttpStatus.BAD_REQUEST.value());
        }

        Optional<User> userOptional = userService.findByDni(userRegisterRequest.getDni());

        if (userOptional.isEmpty())
            return handleRegisterNewCustomer(userRegisterRequest);

        User user = userOptional.get();
        String userState = user.getState().getDescription();
        return switch (userState) {
            case State.ACTIVO -> handleAlreadyActiveCustomer(userRegisterRequest);
            case State.INACTIVO -> handleReactivateCustomer(userRegisterRequest);
            case State.BLOQUEADO -> handleBlockedCustomer(userRegisterRequest);
            default -> throw new IllegalStateException("Unexpected value: " + userState);
        };
    }

    private Response handleRegisterNewCustomer(UserRegisterRequest userRegisterRequest) {
        logger.info("Generar nuevo usuario...");

        try {
            User user = userService.generateUser(userRegisterRequest);
            Address address = addressService.generateAddress(userRegisterRequest, user);

            if (user == null || address == null) {
                logger.error("Failed to create {}.", user == null ? "user" : "address");
                return new Response("Error creating user or address.", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
            logger.info("New user and address created: User={}, Address={}", user, address);

            sendCreateUserEvent(user, userRegisterRequest);
            return new Response("User created!", HttpStatus.CREATED.value());
        } catch (JsonProcessingException e) {
            logger.error("Error sending Kafka event for new user: {}", e.getMessage());
            return new Response("Error sending event.", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private void sendCreateUserEvent(User user, UserRegisterRequest userRegisterRequest) throws JsonProcessingException {
        kafkaService.sendEvent(KafkaEvents.CREATE_USER, user, userRegisterRequest);
        logger.info("Evento enviado para nuevo usuario...");
    }

    private Response handleAlreadyActiveCustomer(UserRegisterRequest userRegisterRequest) {
        logger.info("Usuario ya registrado con el DNI : {}", userRegisterRequest.getDni());
        return new Response("There is an active user with the same DNI.", HttpStatus.CONFLICT.value());
    }

    private Response handleReactivateCustomer(UserRegisterRequest userRegisterRequest) {
        logger.info("Usuario inactivo con DNI : {}", userRegisterRequest.getDni());
        userService.changeStateFromUser(userRegisterRequest, State.ACTIVO);
        return new Response("User reactivated.", HttpStatus.CONFLICT.value());
    }

    private Response handleBlockedCustomer(UserRegisterRequest userRegisterRequest) {
        logger.info("Usuario con el DNI {} está bloqueado", userRegisterRequest.getDni());
        return new Response("The user is blocked.", HttpStatus.FORBIDDEN.value());
    }
}
