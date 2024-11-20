package com.banco.digital.ms_cuentas.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorldsysResponse {
    private int dni;
    private boolean isTerrorist;
}
