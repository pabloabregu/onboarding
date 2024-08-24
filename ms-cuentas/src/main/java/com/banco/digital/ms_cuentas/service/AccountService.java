package com.banco.digital.ms_cuentas.service;

import com.banco.digital.ms_cuentas.model.Account;
import com.banco.digital.ms_cuentas.response.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public interface AccountService extends BaseService<Account, Long> {
    void save(Account account);
    Response externalValidation(String dni, BigDecimal salary) throws URISyntaxException, IOException, InterruptedException;
    boolean existsByAccountNumber(String accountNumber);
}
