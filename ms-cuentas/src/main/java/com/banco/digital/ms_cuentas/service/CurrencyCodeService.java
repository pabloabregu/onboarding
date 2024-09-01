package com.banco.digital.ms_cuentas.service;

import com.banco.digital.ms_cuentas.model.CurrencyCode;

public interface CurrencyCodeService extends BaseService<CurrencyCode, Integer> {
    CurrencyCode findBySymbol(String symbol);
}
