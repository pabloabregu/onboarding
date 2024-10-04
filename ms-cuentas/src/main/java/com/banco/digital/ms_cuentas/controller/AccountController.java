package com.banco.digital.ms_cuentas.controller;

import com.banco.digital.ms_cuentas.request.UserAccountRequest;
import com.banco.digital.ms_cuentas.response.RegisterAccountResponse;
import com.banco.digital.ms_cuentas.service.AccountProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountProcessorService accountProcessorService;

    @Autowired
    public AccountController(AccountProcessorService accountProcessorService) {
        this.accountProcessorService = accountProcessorService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterAccountResponse> create(@RequestBody UserAccountRequest userAccountRequest) throws URISyntaxException, IOException, InterruptedException {
        RegisterAccountResponse response = accountProcessorService.processAccountCreation(userAccountRequest);
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }
}
