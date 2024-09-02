package com.banco.digital.ms_cuentas.controller;

import com.banco.digital.ms_cuentas.request.UserAccountRequest;
import com.banco.digital.ms_cuentas.response.Response;
import com.banco.digital.ms_cuentas.service.AccountEventProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private final AccountEventProcessorService accountEventProcessorService;

    @Autowired
    public AccountController(AccountEventProcessorService accountEventProcessorService) {
        this.accountEventProcessorService = accountEventProcessorService;
    }

    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody UserAccountRequest userAccountRequest) throws URISyntaxException, IOException, InterruptedException {
        accountEventProcessorService.processAccountCreation(userAccountRequest);
        return ResponseEntity.ok(new Response("CREATE!", HttpStatus.CREATED.value()));
    }
}
