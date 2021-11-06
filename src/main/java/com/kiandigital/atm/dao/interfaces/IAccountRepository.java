package com.kiandigital.atm.dao.interfaces;

import com.kiandigital.atm.dao.IGeneralRepository;
import com.kiandigital.atm.model.Account;

import java.math.BigDecimal;

public interface IAccountRepository extends IGeneralRepository<Account> {
    Account loadAccountByUsername(String username);

    BigDecimal getBalanceByUserId(Long userId);

    int withdraw(BigDecimal amount, Long userId);

    int deposit(BigDecimal amount, Long userId);
}
