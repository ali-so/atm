package com.kiandigital.atm.service.interfaces;

import com.kiandigital.atm.model.Account;
import com.kiandigital.atm.service.IGeneralService;

import java.math.BigDecimal;

public interface IAccountService extends IGeneralService<Account> {
    Account loadAccountByUsername();

    BigDecimal getBalanceByUserId();

    BigDecimal withdraw(BigDecimal amount);

    BigDecimal deposit(BigDecimal amount);
}
