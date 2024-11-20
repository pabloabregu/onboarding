package com.banco.digital.ms_cuentas.service;

import com.banco.digital.ms_cuentas.request.UserCardRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaService {

    void sendEvent(UserCardRequest userCardRequest) throws JsonProcessingException;
}
