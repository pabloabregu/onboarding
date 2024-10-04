package com.banco.digital.ms_cuentas.service.impl;

import com.banco.digital.ms_cuentas.model.*;
import com.banco.digital.ms_cuentas.request.UserAccountRequest;
import com.banco.digital.ms_cuentas.request.UserCardRequest;
import com.banco.digital.ms_cuentas.response.RegisterAccountResponse;
import com.banco.digital.ms_cuentas.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private final ProductService productService;

    private final KafkaService kafkaService;

    @Autowired
    public AccountProcessorServiceImpl(AccountService accountService, CurrencyCodeService currencyCodeService,
                                       AccountStatusService accountStatusService,
                                       ExternalValidationService externalValidationService,
                                       ProductService productService,
                                       KafkaService kafkaService) {
        this.accountService = accountService;
        this.currencyCodeService = currencyCodeService;
        this.accountStatusService = accountStatusService;
        this.externalValidationService = externalValidationService;
        this.productService = productService;
        this.kafkaService = kafkaService;
    }

    @Override
    public RegisterAccountResponse processAccountCreation(UserAccountRequest userAccountRequest) throws URISyntaxException, IOException, InterruptedException {
        logger.info("Generar cuentas...");
        String dni = userAccountRequest.getDni();
        Integer persNum = userAccountRequest.getPersNum();
        BigDecimal salary = userAccountRequest.getSalary();

        logger.info("Validación externa de dni {} ...", dni);
        ExternalValidationResult externalValidationResult = externalValidationService.getValidationResults(dni);

        logger.info("Validando tipo producto...");

        Product product = productService.generateProduct(externalValidationResult, salary);
        logger.info("Validando tipo producto {}", product.toString());

        CurrencyCode currencyCode = currencyCodeService.findBySymbol("USD");
        AccountStatus accountStatus = accountStatusService.findByDetail("Activa");

        Account account = accountService.generateAccount(persNum, currencyCode, accountStatus, salary);

        logger.info("Cuenta creada...");
        sendCreateUserEvent(account);

        return new RegisterAccountResponse("Account registered successfully!", HttpStatus.CREATED.value());
    }

    private void sendCreateUserEvent(Account account) throws JsonProcessingException {
        UserCardRequest userCardRequest = UserCardRequest.builder()
                .accountNumber(account.getAccountNumber()).build();
        kafkaService.sendEvent(userCardRequest);
    }
}
