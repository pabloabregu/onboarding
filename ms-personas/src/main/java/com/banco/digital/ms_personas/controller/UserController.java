package com.banco.digital.ms_personas.controller;

import com.banco.digital.ms_personas.request.UserRequest;
import com.banco.digital.ms_personas.response.Response;
import com.banco.digital.ms_personas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Crea un nuevo usuario y realiza el alta de producto.
     *
     * @param userRequest Contiene los datos del usuario a crear.
     * @return ResponseEntity con la respuesta de la operación.
     */
    @PostMapping("/createUser")
    public ResponseEntity<Response> createUser(@RequestBody UserRequest userRequest) {
        Response response = userService.createUser(userRequest);
        return ResponseEntity.ok(response);
    }
}
