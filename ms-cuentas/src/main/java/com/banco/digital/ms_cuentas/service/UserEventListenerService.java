package com.banco.digital.ms_cuentas.service;

import com.banco.digital.ms_cuentas.request.UserAccountEvent;

import java.io.IOException;
import java.net.URISyntaxException;

public interface UserEventListenerService {
    void processClientEvent(UserAccountEvent userAccountEvent) throws URISyntaxException, IOException, InterruptedException;
}
