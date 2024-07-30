package com.banco.digital.ms_cuentas.service;

import com.banco.digital.ms_cuentas.model.Account;
import com.banco.digital.ms_cuentas.response.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public interface AccountService extends BaseService<Account, Long>{
    public Response createAccount(String salary);

    Response externalValidation(String dni) throws URISyntaxException, IOException, InterruptedException;
    
    Response externalValidation(String dni, BigDecimal salary) throws URISyntaxException, IOException, InterruptedException;
}
