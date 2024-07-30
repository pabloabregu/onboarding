package com.banco.digital.ms_cuentas.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAccountRequest{
    private Long personNumber;
    private BigDecimal salary;
}
