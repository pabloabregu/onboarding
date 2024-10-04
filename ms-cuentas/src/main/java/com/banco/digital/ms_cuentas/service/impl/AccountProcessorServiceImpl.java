package com.banco.digital.ms_cuentas.service.impl;

import com.banco.digital.ms_cuentas.model.Account;
import com.banco.digital.ms_cuentas.model.AccountStatus;
import com.banco.digital.ms_cuentas.model.CurrencyCode;
import com.banco.digital.ms_cuentas.model.ExternalValidationResult;
import com.banco.digital.ms_cuentas.request.UserAccountRequest;
import com.banco.digital.ms_cuentas.response.RegisterAccountResponse;
import com.banco.digital.ms_cuentas.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

@Service
public class AccountProcessorServiceImpl implements AccountProcessorService {
    private final Logger logger = LoggerFactory.getLogger(AccountProcessorServiceImpl.class);

    private final AccountService accountService;
    private final CurrencyCodeService currencyCodeService;
    private final AccountStatusService accountStatusService;
    private final ExternalValidationService externalValidationService;

    @Autowired
    public AccountProcessorServiceImpl(AccountService accountService, CurrencyCodeService currencyCodeService,
                                       AccountStatusService accountStatusService,
                                       ExternalValidationService externalValidationService) {
        this.accountService = accountService;
        this.currencyCodeService = currencyCodeService;
        this.accountStatusService = accountStatusService;
        this.externalValidationService = externalValidationService;
    }

    @Override
    public RegisterAccountResponse processAccountCreation(UserAccountRequest userAccountRequest) throws URISyntaxException, IOException, InterruptedException {
        logger.info("Generar cuentas...");

        String dni = userAccountRequest.getDni();
        Long persNum = userAccountRequest.getPersNum();
        BigDecimal salary = userAccountRequest.getSalary();

        ExternalValidationResult externalValidationResult = externalValidationService.getValidationResults(dni);
        logger.info("External validation to DNI {} : {}", dni, externalValidationResult.toString());

        // select product

        CurrencyCode currencyCode = currencyCodeService.findBySymbol("USD");
        AccountStatus accountStatus = accountStatusService.findByDetail("Activo");

        Account account = accountService.generateAccount(persNum, currencyCode, accountStatus, salary);
        logger.info("Creat account : {}", account);

        // Send event
        return null;
    }
}
