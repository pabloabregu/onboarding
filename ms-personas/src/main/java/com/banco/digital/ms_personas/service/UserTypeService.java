package com.banco.digital.ms_personas.service;

import com.banco.digital.ms_personas.model.UserType;

public interface UserTypeService extends BaseService<UserType, Integer> {
    UserType findByDescription(String name);
}
