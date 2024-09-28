package com.banco.digital.ms_cuentas.service;

import com.banco.digital.ms_cuentas.model.ExternalValidationResult;
import com.banco.digital.ms_cuentas.model.Product;

import java.math.BigDecimal;

public interface ProductService {
    String generateProduct(boolean isRenaperAuthorized, boolean isWorldSysTerrorist, double verazScore, BigDecimal salary);

    Product generateProduct(ExternalValidationResult externalValidationResult, BigDecimal salary);
}
