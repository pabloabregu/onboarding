package com.banco.digital.ms_personas.service.impl;

import com.banco.digital.ms_personas.enums.State;
import com.banco.digital.ms_personas.model.Address;
import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.request.UserRequest;
import com.banco.digital.ms_personas.response.Response;
import com.banco.digital.ms_personas.service.AddressService;
import com.banco.digital.ms_personas.service.KafkaService;
import com.banco.digital.ms_personas.service.OnboardingService;
import com.banco.digital.ms_personas.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OnboardingServiceImpl implements OnboardingService {

    private static final Logger logger = LoggerFactory.getLogger(OnboardingServiceImpl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private KafkaService kafkaService;

    @Value("${kafka-topic.alta-usuario}")
    private String nameKafkaTopic;

    @Override
    public Response register(UserRequest userRequest) throws JsonProcessingException {
        // validate user
        State stateUser = userService.validateUser(userRequest);

        switch (stateUser) {
            case ACTIVO -> {
                return new Response("There is an active user with the same DNI.", HttpStatus.CONFLICT.value());
            }
            case INACTIVO -> {
                return new Response("The user is inactive.", HttpStatus.CONFLICT.value()); //volver a generar alta ?
            }
            case BLOQUEADO -> {
                return new Response("The user is blocked.", HttpStatus.FORBIDDEN.value());
            }
            case CANCELADO -> {
                return new Response("The user is canceled.", HttpStatus.FORBIDDEN.value());
            }
            case SUSPENDIDO -> {
                return new Response("The user is suspended.", HttpStatus.FORBIDDEN.value()); //volver a generar alta ?
            }
            case NO_EXISTE -> {
                //create user and address
                User user = userService.generateUser(userRequest);
                Address address = addressService.generateAddress(userRequest, user);

                logger.trace("NEW USER : {}", user);
                logger.trace("NEW ADDRESS : {}", address);

                //kafka event
                kafkaService.sendEvent(nameKafkaTopic, user, userRequest);

                return new Response("User created!", HttpStatus.CREATED.value());
            }
            default -> throw new IllegalStateException("Error");
        }
    }
}
