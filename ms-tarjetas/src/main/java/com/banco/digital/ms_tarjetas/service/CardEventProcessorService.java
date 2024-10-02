package com.banco.digital.ms_tarjetas.service;

import com.banco.digital.ms_tarjetas.model.Card;
import com.banco.digital.ms_tarjetas.request.IssueCardRequest;
import org.springframework.stereotype.Service;

@Service
public interface CardEventProcessorService {
    Card processCardCreation(IssueCardRequest request);
}
