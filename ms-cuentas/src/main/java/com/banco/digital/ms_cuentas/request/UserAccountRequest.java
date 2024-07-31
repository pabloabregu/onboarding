package com.banco.digital.ms_cuentas.request;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAccountRequest implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    private Long personNumber;
    private BigDecimal salary;
}
