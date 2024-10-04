package com.banco.digital.ms_cuentas.service.impl;

import com.banco.digital.ms_cuentas.model.Account;
import com.banco.digital.ms_cuentas.model.AccountStatus;
import com.banco.digital.ms_cuentas.model.CurrencyCode;
import com.banco.digital.ms_cuentas.repository.AccountRepository;
import com.banco.digital.ms_cuentas.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return accountRepository.findById(id);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        return accountRepository.existsByAccountNumber(accountNumber);
    }

    @Override
    public String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        do {
            SecureRandom secureRandom = new SecureRandom();
            // Generar el primer dígito como un número no cero
            accountNumber.append(secureRandom.nextInt(9) + 1);
            // Generar el resto de los dígitos
            for (int i = 1; i < 10; i++) {
                accountNumber.append(secureRandom.nextInt(10));
            }
        } while (existsByAccountNumber(accountNumber.toString()));
        return accountNumber.toString();
    }

    @Override
    public Account generateAccount(Integer persNum, CurrencyCode currencyCode, AccountStatus accountStatus, BigDecimal salary) {
        String accountNumber = generateAccountNumber();

        Account account = Account.builder()
                .accountNumber(accountNumber)
                .personNumber(persNum)
                .currency(currencyCode)
                .status(accountStatus)
                .salary(salary)
                .build();
        save(account);
        return account;
    }
}
