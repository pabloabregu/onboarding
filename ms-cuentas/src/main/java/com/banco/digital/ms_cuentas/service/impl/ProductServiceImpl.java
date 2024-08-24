package com.banco.digital.ms_cuentas.service.impl;

import com.banco.digital.ms_cuentas.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public String generateProduct(boolean isRenaperAuthorized, boolean isWorldSysTerrorist, double verazScore, BigDecimal salary) {
        if (salary.compareTo(BigDecimal.ZERO) <= 0)
            return "No puede ser menor.";

        if (!isRenaperAuthorized && salary.compareTo(new BigDecimal("200000")) >= 0) {
            return "Cuenta  PESOS $";
        }

        if (isRenaperAuthorized && !isWorldSysTerrorist) {
            if (verazScore <= 0.1 && salary.compareTo(new BigDecimal("999000")) >= 0) {
                return "Cuenta Dólar y pesos+ Tarjeta Black";
            }
            if (verazScore <= 0.5 && salary.compareTo(new BigDecimal("999000")) >= 0) {
                return "Cuenta Dólar y pesos+ Tarjeta Gold";
            }
            if (verazScore <= 1.0 && salary.compareTo(new BigDecimal("827000")) >= 0) {
                return "Cuenta Dólar y Pesos+ Tarjeta Basic";
            }
            if (verazScore <= 1.5 && salary.compareTo(new BigDecimal("446000")) >= 0) {
                return "Cuenta Dólar y Pesos";
            }
        }
        return "Condiciones no cumplidas";
    }

}
