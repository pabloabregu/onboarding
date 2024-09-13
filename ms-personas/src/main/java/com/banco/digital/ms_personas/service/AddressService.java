package com.banco.digital.ms_personas.service;

import com.banco.digital.ms_personas.model.Address;
import com.banco.digital.ms_personas.model.User;
import com.banco.digital.ms_personas.request.UserRegisterRequest;

public interface AddressService extends BaseService<Address, Long>{
    Address generateAddress(UserRegisterRequest userRegisterRequest, User user);
}
