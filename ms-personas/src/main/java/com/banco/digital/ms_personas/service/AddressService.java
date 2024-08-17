package com.banco.digital.ms_personas.service;

import com.banco.digital.ms_personas.model.Address;
import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.request.UserRequest;

public interface AddressService {
    Address generateAddress(UserRequest userRequest, User user);
}
