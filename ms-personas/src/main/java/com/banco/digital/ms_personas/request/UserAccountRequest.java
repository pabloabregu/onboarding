package com.banco.digital.ms_personas.request;

import com.banco.digital.ms_personas.model.User;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAccountRequest implements Serializable {
    private Long persNum;
    private BigDecimal salary;
}
