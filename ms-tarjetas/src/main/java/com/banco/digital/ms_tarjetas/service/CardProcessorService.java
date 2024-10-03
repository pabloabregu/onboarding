package com.banco.digital.ms_tarjetas.service;

import com.banco.digital.ms_tarjetas.request.RegisterCardRequest;
import com.banco.digital.ms_tarjetas.response.RegisterCardResponse;
import org.springframework.stereotype.Service;

@Service
public interface CardProcessorService {
    RegisterCardResponse processCardCreation(RegisterCardRequest request);
}
