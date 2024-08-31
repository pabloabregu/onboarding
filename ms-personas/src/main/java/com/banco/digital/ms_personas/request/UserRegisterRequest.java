package com.banco.digital.ms_personas.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    private String firstname;
    private String lastname;
    private String dni;
    private String street;
    private Integer number;
    private String province;
    private String locality;
    private BigDecimal salary;
}
