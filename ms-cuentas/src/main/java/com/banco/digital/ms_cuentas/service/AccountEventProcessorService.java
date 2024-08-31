package com.banco.digital.ms_cuentas.service;

import com.banco.digital.ms_cuentas.request.UserAccountRequest;

import java.io.IOException;
import java.net.URISyntaxException;

public interface AccountEventProcessorService {
    void processAccountCreation(UserAccountRequest userAccountRequest) throws URISyntaxException, IOException, InterruptedException;
}
