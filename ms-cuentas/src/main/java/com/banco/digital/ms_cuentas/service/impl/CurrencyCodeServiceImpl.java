package com.banco.digital.ms_cuentas.service.impl;

import com.banco.digital.ms_cuentas.model.CurrencyCode;
import com.banco.digital.ms_cuentas.repository.CurrencyCodeRepository;
import com.banco.digital.ms_cuentas.service.CurrencyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyCodeServiceImpl implements CurrencyCodeService {

    @Autowired
    private CurrencyCodeRepository currencyCodeRepository;

    @Override
    public List<CurrencyCode> findAll() {
        return currencyCodeRepository.findAll();
    }

    @Override
    public Optional<CurrencyCode> findById(Long id) {
        return currencyCodeRepository.findById(id);
    }
}
