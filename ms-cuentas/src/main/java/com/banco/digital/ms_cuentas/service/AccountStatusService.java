package com.banco.digital.ms_cuentas.service;

import com.banco.digital.ms_cuentas.model.AccountStatus;

public interface AccountStatusService extends BaseService<AccountStatus, Integer> {
    AccountStatus findByDetail(String detail);
}
