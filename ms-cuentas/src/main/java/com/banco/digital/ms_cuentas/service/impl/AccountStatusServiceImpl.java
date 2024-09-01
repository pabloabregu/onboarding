package com.banco.digital.ms_cuentas.service.impl;

import com.banco.digital.ms_cuentas.model.AccountStatus;
import com.banco.digital.ms_cuentas.repository.AccountStatusRepository;
import com.banco.digital.ms_cuentas.service.AccountStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountStatusServiceImpl implements AccountStatusService {

    private final AccountStatusRepository accountStatusRepository;

    @Autowired
    public AccountStatusServiceImpl(AccountStatusRepository accountStatusRepository) {
        this.accountStatusRepository = accountStatusRepository;
    }

    @Override
    public List<AccountStatus> findAll() {
        return accountStatusRepository.findAll();
    }

    @Override
    public Optional<AccountStatus> findById(Integer id) {
        return accountStatusRepository.findById(id);
    }

    @Override
    public AccountStatus findByDetail(String detail) {
        return accountStatusRepository.findByDetail(detail);
    }
}
