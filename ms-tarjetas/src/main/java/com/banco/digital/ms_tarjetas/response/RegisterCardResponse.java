package com.banco.digital.ms_tarjetas.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterCardResponse extends Response {
    public RegisterCardResponse(String message, Integer httpStatusCode) {
        super(message, httpStatusCode);
    }
}
