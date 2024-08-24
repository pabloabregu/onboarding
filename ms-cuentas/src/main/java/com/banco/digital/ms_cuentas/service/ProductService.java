package com.banco.digital.ms_cuentas.service;

import java.math.BigDecimal;

public interface ProductService {
    String generateProduct(boolean isRenaperAuthorized, boolean isWorldSysTerrorist, double verazScore, BigDecimal salary);
}
