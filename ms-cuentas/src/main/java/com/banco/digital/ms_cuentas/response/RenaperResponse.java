package com.banco.digital.ms_cuentas.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RenaperResponse {
    private int dni;
    private boolean isAuthorize;
}
