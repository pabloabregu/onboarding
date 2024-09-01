package com.banco.digital.ms_cuentas.service.impl;

import com.banco.digital.ms_cuentas.model.Account;
import com.banco.digital.ms_cuentas.model.AccountStatus;
import com.banco.digital.ms_cuentas.model.CurrencyCode;
import com.banco.digital.ms_cuentas.model.ExternalValidationResult;
import com.banco.digital.ms_cuentas.request.UserAccountRequest;
import com.banco.digital.ms_cuentas.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

@Service
public class AccountEventProcessorServiceImpl implements AccountEventProcessorService {
    private final Logger logger = LoggerFactory.getLogger(AccountEventProcessorServiceImpl.class);

    @Autowired
    private ExternalValidationService externalValidationService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CurrencyCodeService currencyCodeService;

    @Autowired
    private AccountStatusService accountStatusService;

    @Override
    public void processAccountCreation(UserAccountRequest userAccountRequest) throws URISyntaxException, IOException, InterruptedException {
        logger.info("User Request : {}", userAccountRequest.toString());

        String dni = userAccountRequest.getDni();
        Long persNum = userAccountRequest.getPersNum();
        BigDecimal salary = userAccountRequest.getSalary();

        ExternalValidationResult externalValidationResult = externalValidationService.getValidationResults(dni);
        logger.info("External validation to DNI {} : {}", dni, externalValidationResult.toString());

        CurrencyCode currencyCode = currencyCodeService.findBySymbol("USD");
        AccountStatus accountStatus = accountStatusService.findByDetail("Activo");

        Account account = accountService.generateAccount(persNum, currencyCode, accountStatus, salary);

        //String product = productService.generateProduct(renaper, worldSys, veraz, salary);
        //List<String> productos = determinarProductos(renaper, worldSys, veraz, sueldoBruto);
        //productos.forEach(producto -> kafkaTemplate.send("productoEventos", new ProductoEvento(evento.getId(), producto)));
    }
}
