package com.banco.digital.ms_tarjetas.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCardResponse extends Response {
    public RegisterCardResponse(String message, Integer httpStatusCode) {
        super(message, httpStatusCode);
    }
}
