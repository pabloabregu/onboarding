package com.banco.digital.ms_personas.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterUserResponse extends Response {
    public RegisterUserResponse(String message, Integer httpStatusCode) {
        super(message, httpStatusCode);
    }
}
