package com.banco.digital.ms_cuentas.response;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VerazResponse {
    private int dni;
    private double score;
}
