package com.banco.digital.ms_cuentas.service;

import com.banco.digital.ms_cuentas.model.Account;
import com.banco.digital.ms_cuentas.model.AccountStatus;
import com.banco.digital.ms_cuentas.model.CurrencyCode;

import java.math.BigDecimal;

public interface AccountService extends BaseService<Account, Long> {
    void save(Account account);

    boolean existsByAccountNumber(String accountNumber);

    Account generateAccount(Long persNum, CurrencyCode currencyCode, AccountStatus accountStatus, BigDecimal salary);

    String generateAccountNumber();
}
