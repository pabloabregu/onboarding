package com.banco.digital.ms_cuentas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExternalValidationResult {
    private boolean renaperAuthorized;
    private boolean isTerrorist;
    private double verazScore;
}
