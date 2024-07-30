package com.banco.digital.ms_cuentas.service.impl;

import com.banco.digital.ms_cuentas.model.Account;
import com.banco.digital.ms_cuentas.response.RenaperResponse;
import com.banco.digital.ms_cuentas.response.Response;
import com.banco.digital.ms_cuentas.response.VerazResponse;
import com.banco.digital.ms_cuentas.response.WorldsysResponse;
import com.banco.digital.ms_cuentas.service.AccountService;
import com.banco.digital.ms_cuentas.service.ExternalValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    ExternalValidationService externalValidationService;

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public Response createAccount(String salary) {
        return null;
    }

    @Override
    public Response externalValidation(String dni) throws URISyntaxException, IOException, InterruptedException {
        return null;
    }

    @Override
    public Response externalValidation(String dni, BigDecimal salary) throws URISyntaxException, IOException, InterruptedException {
        RenaperResponse renaperResponse = externalValidationService.getRenaperResponse(dni);
        WorldsysResponse worldsysResponse = externalValidationService.getWorldsysResponse(dni);
        VerazResponse verazResponse = externalValidationService.getVerazResponse(dni);

        if (renaperResponse == null || worldsysResponse == null || verazResponse == null) {
            return new Response("Error during validation", 500);
        }

        String message = String.format(
                "DNI: %s, Renaper: %b, Worldsys: %s, Veraz: %s",
                dni,
                renaperResponse.isAuthorize(),
                worldsysResponse.isTerrorist(),
                verazResponse.getScore()
        );
        System.out.println(message);

        String product = getProduct(renaperResponse.isAuthorize(),worldsysResponse.isTerrorist(), verazResponse.getScore(), salary);
        return new Response(product, 200);
    }

    public String getProduct(boolean isRenaperAuthorized, boolean isWorldSysTerrorist, double verazScore, BigDecimal sueldoBruto) {
        System.out.println(sueldoBruto);

        if (sueldoBruto.compareTo(BigDecimal.ZERO) <= 0)
            return "No puede ser menor.";

        if (!isRenaperAuthorized && sueldoBruto.compareTo(new BigDecimal("200000")) >= 0){
            return "Cuenta  PESOS $";
        }

        if (isRenaperAuthorized && !isWorldSysTerrorist) {
            if (verazScore <= 0.1 && sueldoBruto.compareTo(new BigDecimal("999000")) >= 0) {
                return "Cuenta Dólar y pesos+ Tarjeta Black";
            }
            if (verazScore <= 0.5 && sueldoBruto.compareTo(new BigDecimal("999000")) >= 0) {
                return "Cuenta Dólar y pesos+ Tarjeta Gold";
            }
            if (verazScore <= 1.0 && sueldoBruto.compareTo(new BigDecimal("827000")) >= 0){
                return "Cuenta Dólar y Pesos+ Tarjeta Basic";
            }
            if (verazScore <= 1.5 && sueldoBruto.compareTo(new BigDecimal("446000")) >= 0){
                return "Cuenta Dólar y Pesos";
            }
        }
        return "Condiciones no cumplidas";
    }

}
