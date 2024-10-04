package com.banco.digital.ms_cuentas.service;

import com.banco.digital.ms_cuentas.request.UserAccountRequest;
import com.banco.digital.ms_cuentas.response.RegisterAccountResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public interface AccountProcessorService {
    RegisterAccountResponse processAccountCreation(UserAccountRequest userAccountRequest) throws URISyntaxException, IOException, InterruptedException;
}
