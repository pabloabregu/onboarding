package com.banco.digital.ms_cuentas.service;

import com.banco.digital.ms_cuentas.response.RenaperResponse;
import com.banco.digital.ms_cuentas.response.VerazResponse;
import com.banco.digital.ms_cuentas.response.WorldsysResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ExternalValidationService {
    RenaperResponse getRenaperResponse(String dni) throws URISyntaxException, IOException, InterruptedException;

    WorldsysResponse getWorldsysResponse(String dni) throws URISyntaxException, IOException, InterruptedException;

    VerazResponse getVerazResponse(String dni) throws URISyntaxException, IOException, InterruptedException;
}
