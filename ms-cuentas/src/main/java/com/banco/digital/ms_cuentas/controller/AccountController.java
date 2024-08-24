package com.banco.digital.ms_cuentas.controller;

import com.banco.digital.ms_cuentas.model.AccountStatus;
import com.banco.digital.ms_cuentas.model.CurrencyCode;
import com.banco.digital.ms_cuentas.request.Request;
import com.banco.digital.ms_cuentas.request.UserAccountEvent;
import com.banco.digital.ms_cuentas.response.Response;
import com.banco.digital.ms_cuentas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    CurrencyCodeService currencyCodeService;

    @Autowired
    AccountStatusService accountStatusService;

    @Autowired
    private UserEventListenerService userEventListenerService;

    @PostMapping("/validateExternal")
    public ResponseEntity<Response> externalValidation(@RequestBody Request request) throws URISyntaxException, IOException, InterruptedException {
        return ResponseEntity.ok(accountService.externalValidation(request.getDni(), BigDecimal.valueOf(request.getSalary())));
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody UserAccountEvent userAccountEvent) throws URISyntaxException, IOException, InterruptedException {
        userEventListenerService.processClientEvent(userAccountEvent);
        return ResponseEntity.ok("CREATE");
    }
}
