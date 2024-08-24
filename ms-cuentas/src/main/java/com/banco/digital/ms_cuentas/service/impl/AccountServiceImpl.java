package com.banco.digital.ms_cuentas.service.impl;

import com.banco.digital.ms_cuentas.model.Account;
import com.banco.digital.ms_cuentas.repository.AccountRepository;
import com.banco.digital.ms_cuentas.response.Response;
import com.banco.digital.ms_cuentas.service.AccountService;
import com.banco.digital.ms_cuentas.service.ExternalValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    ExternalValidationService externalValidationService;

    @Override
    public List<Account> findAll() {
        return null;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Response externalValidation(String dni, BigDecimal salary) throws URISyntaxException, IOException, InterruptedException {
//        RenaperResponse renaperResponse = externalValidationService.getRenaperResponse(dni);
//        WorldsysResponse worldsysResponse = externalValidationService.getWorldsysResponse(dni);
//        VerazResponse verazResponse = externalValidationService.getVerazResponse(dni);
//
//        if (renaperResponse == null || worldsysResponse == null || verazResponse == null) {
//            return new Response("Error during validation", 500);
//        }
//
//        String message = String.format(
//                "DNI: %s, Renaper: %b, Worldsys: %s, Veraz: %s",
//                dni,
//                renaperResponse.isAuthorize(),
//                worldsysResponse.isTerrorist(),
//                verazResponse.getScore()
//        );
//        System.out.println(message);
//
//        String product = getProduct(renaperResponse.isAuthorize(),worldsysResponse.isTerrorist(), verazResponse.getScore(), salary);
//        return new Response(product, 200);
        return null;
    }
    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        return accountRepository.existsByAccountNumber(accountNumber);
    }
}
