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
public class UserAccountRequest {
    private Long persNum;
    private String dni;
    private BigDecimal salary;
    private String event;
}
