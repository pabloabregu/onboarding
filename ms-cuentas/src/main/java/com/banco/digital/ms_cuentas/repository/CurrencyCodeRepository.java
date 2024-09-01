package com.banco.digital.ms_cuentas.repository;

import com.banco.digital.ms_cuentas.model.CurrencyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyCodeRepository extends JpaRepository<CurrencyCode, Integer> {
    CurrencyCode findBySymbol(String symbol);
}
