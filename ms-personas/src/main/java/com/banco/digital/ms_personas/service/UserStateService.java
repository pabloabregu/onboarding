package com.banco.digital.ms_personas.service;

import com.banco.digital.ms_personas.model.UserState;

public interface UserStateService extends BaseService<UserState, Long> {
    UserState findByDescription(String string);
}
