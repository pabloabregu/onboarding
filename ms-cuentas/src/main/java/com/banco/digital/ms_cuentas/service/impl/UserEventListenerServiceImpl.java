package com.banco.digital.ms_cuentas.service.impl;

import com.banco.digital.ms_cuentas.model.Account;
import com.banco.digital.ms_cuentas.model.AccountStatus;
import com.banco.digital.ms_cuentas.model.CurrencyCode;
import com.banco.digital.ms_cuentas.request.UserAccountEvent;
import com.banco.digital.ms_cuentas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserEventListenerServiceImpl implements UserEventListenerService {

    @Autowired
    private ExternalValidationService externalValidationService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CurrencyCodeService currencyCodeService;

    @Autowired
    private AccountStatusService accountStatusService;

    @Override
    public void processClientEvent(UserAccountEvent userAccountEvent) throws URISyntaxException, IOException, InterruptedException {
        if (userAccountEvent.getEvent().equals("new-user")) {
            String dni = userAccountEvent.getDni();
            BigDecimal salary = userAccountEvent.getSalary();

            boolean renaper = externalValidationService.getRenaperResponse(dni).isAuthorize();
            boolean worldSys = externalValidationService.getWorldsysResponse(dni).isTerrorist();
            double veraz = externalValidationService.getVerazResponse(dni).getScore();

            //esto debe generar el tipo de producto, teniendo en cuenta la divisa
            String product = productService.generateProduct(renaper, worldSys, veraz, salary);

            System.out.println("Producto : " + product);

            Optional<CurrencyCode> currencyCode = currencyCodeService.findById(1L);
            Optional<AccountStatus> accountStatus = accountStatusService.findById(1L);

            System.out.println("Currency Code: " + currencyCode);
            System.out.println("Account Status: " + accountStatus);

            if (currencyCode.isPresent() && accountStatus.isPresent()) {
                String accountNumber = generateAccountNumber();
                String numcue = UUID.randomUUID().toString().replace("-", "").toUpperCase();
                System.out.println("NUMCUE : " + numcue);
                System.out.println("Number : " + accountNumber);

                if (numcue.length() > 8) {
                    numcue = numcue.substring(0, 8);
                }

                Account account = Account.builder()
                        .accountNumber(accountNumber)
                        .personNumber(userAccountEvent.getPersNum())
                        .currency(currencyCode.get())
                        .status(accountStatus.get())
                        .salary(salary)
                        .build();
                System.out.println("Account : " + account);
                accountService.save(account);
            }
        }
        //List<String> productos = determinarProductos(renaper, worldSys, veraz, sueldoBruto);
        //productos.forEach(producto -> kafkaTemplate.send("productoEventos", new ProductoEvento(evento.getId(), producto)));
    }

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
        } while (accountService.existsByAccountNumber(accountNumber.toString()));

        return accountNumber.toString();
    }
}
