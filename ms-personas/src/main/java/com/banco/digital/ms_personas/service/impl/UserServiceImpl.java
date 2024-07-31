package com.banco.digital.ms_personas.service.impl;

import com.banco.digital.ms_personas.enums.State;
import com.banco.digital.ms_personas.enums.Type;
import com.banco.digital.ms_personas.model.Address;
import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.model.UserState;
import com.banco.digital.ms_personas.model.UserType;
import com.banco.digital.ms_personas.repository.AddressRepository;
import com.banco.digital.ms_personas.repository.UserRepository;
import com.banco.digital.ms_personas.repository.UserStateRepository;
import com.banco.digital.ms_personas.repository.UserTypeRepository;
import com.banco.digital.ms_personas.request.UserAccountRequest;
import com.banco.digital.ms_personas.request.UserRequest;
import com.banco.digital.ms_personas.response.Response;
import com.banco.digital.ms_personas.response.UserStateValidationResponse;
import com.banco.digital.ms_personas.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserStateRepository userStateRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

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
    public Response createUser(UserRequest userRequest) throws JsonProcessingException {
        UserStateValidationResponse stateValidationResponse = validateUserData(userRequest);

        return switch (stateValidationResponse.getState()) {
            case ACTIVO -> new Response("There is an active user with the same DNI.", HttpStatus.CONFLICT.value());
            case INACTIVO ->
                    new Response("The user is inactive.", HttpStatus.CONFLICT.value()); //volver a generar alta ?
            case BLOQUEADO -> new Response("The user is blocked.", HttpStatus.FORBIDDEN.value());
            case CANCELADO -> new Response("The user is canceled.", HttpStatus.FORBIDDEN.value());
            case SUSPENDIDO ->
                    new Response("The user is suspended.", HttpStatus.FORBIDDEN.value()); //volver a generar alta ?
            case NO_EXISTE -> {
                User user = generateUser(userRequest);
                UserAccountRequest userAccountRequest = UserAccountRequest.builder()
                        .persNum(user.getIdUser())
                        .salary(userRequest.getSalary())
                        .build();

                String jsonMessage = new ObjectMapper().writeValueAsString(userAccountRequest);

                kafkaTemplate.send("alta-usuario", jsonMessage);

                yield new Response("Usuario Creado!", HttpStatus.CREATED.value());
            }
        };
    }

    @Override
    public UserStateValidationResponse validateUserData(UserRequest userRequest) {
        Optional<User> userOptional = findByDni(userRequest.getDni());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String userState = user.getState().getDescription();
            State state = switch (userState.toUpperCase()) {
                case "ACTIVO" -> State.ACTIVO;
                case "INACTIVO" -> State.INACTIVO;
                case "BLOQUEADO" -> State.BLOQUEADO;
                default -> State.NO_EXISTE;
            };
            return new UserStateValidationResponse(state);
        }
        return new UserStateValidationResponse(State.NO_EXISTE);
    }

    private User generateUser(UserRequest userRequest) {
        UserState userState = userStateRepository.findByDescription(State.ACTIVO.name());
        UserType userType = userTypeRepository.findByDescription(Type.CLIENTE.name());
        User user = User.builder()
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .dni(userRequest.getDni())
                .state(userState)
                .type(userType)
                .build();
        userRepository.save(user);
        Address address = Address.builder()
                .idUser(user.getIdUser())
                .street(userRequest.getStreet())
                .number(userRequest.getNumber())
                .province(userRequest.getProvince())
                .locality(userRequest.getLocality())
                .build();
        addressRepository.save(address);

        return user;
    }
}