package com.banco.digital.ms_cuentas.service.impl;

import com.banco.digital.ms_cuentas.model.ExternalValidationResult;
import com.banco.digital.ms_cuentas.model.Product;
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

    @Override
    public Product generateProduct(ExternalValidationResult externalValidationResult, BigDecimal salary) {
        boolean isRenaperAuthorized = externalValidationResult.isRenaperAuthorized();
        boolean isWorldSysTerrorist = externalValidationResult.isTerrorist();
        double verazScore = externalValidationResult.getVerazScore();

        if (salary.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Salary must be positive.");

        if (!isRenaperAuthorized && salary.compareTo(new BigDecimal("200000")) >= 0)
            return new Product("Cuenta PESOS $", "ARS");

        if (isRenaperAuthorized && !isWorldSysTerrorist) {
            if (verazScore <= 0.1 && salary.compareTo(new BigDecimal("999000")) >= 0) {
                return new Product("Cuenta Dólar y pesos+ Tarjeta Black", "USD");
            }
            if (verazScore <= 0.5 && salary.compareTo(new BigDecimal("999000")) >= 0) {
                return new Product("Cuenta Dólar y pesos+ Tarjeta Gold", "USD");
            }
            if (verazScore <= 1.0 && salary.compareTo(new BigDecimal("827000")) >= 0) {
                return new Product("Cuenta Dólar y Pesos+ Tarjeta Basic", "USD");
            }
            if (verazScore <= 1.5 && salary.compareTo(new BigDecimal("446000")) >= 0) {
                return new Product("Cuenta Dólar y Pesos", "USD");
            }
        }
        return null;
    }

}
