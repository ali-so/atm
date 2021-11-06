package com.kiandigital.atm.service.impl;

import com.kiandigital.atm.common.exceptions.ApplicationException;
import com.kiandigital.atm.dao.IGeneralRepository;
import com.kiandigital.atm.dao.interfaces.IAccountRepository;
import com.kiandigital.atm.model.Account;
import com.kiandigital.atm.model.AccountHistory;
import com.kiandigital.atm.model.TransactionType;
import com.kiandigital.atm.security.SecurityUtils;
import com.kiandigital.atm.service.GeneralService;
import com.kiandigital.atm.service.interfaces.IAccountHistoryService;
import com.kiandigital.atm.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService extends GeneralService<Account> implements IAccountService {

    @Autowired
    private IAccountRepository iAccountRepository;

    @Autowired
    private IAccountHistoryService iAccountHistoryService;

    @Override
    protected IGeneralRepository<Account> getGeneralRepository() {
        return iAccountRepository;
    }

    @Override
    public Account loadAccountByUsername() {
        return iAccountRepository.loadAccountByUsername(SecurityUtils.getAuthenticatedUser().getUsername());
    }

    @Override
    public BigDecimal getBalanceByUserId() {
        return iAccountRepository.getBalanceByUserId(SecurityUtils.getAuthenticatedUserId());
    }

    @Override
    @Transactional
    public BigDecimal withdraw(BigDecimal amount) {
        Account account = loadAccountByUsername();
        if(account.getBalance().subtract(amount).compareTo(new BigDecimal(10000)) == -1) {
            throw new ApplicationException("Not enough balance!");
        }

        iAccountRepository.withdraw(amount, SecurityUtils.getAuthenticatedUserId());

        AccountHistory accountHistory = new AccountHistory(account, TransactionType.withdraw, amount);
        iAccountHistoryService.add(accountHistory);
        clearSession();
        return getBalanceByUserId();
    }

    @Override
    @Transactional
    public BigDecimal deposit(BigDecimal amount) {
        Account account = loadAccountByUsername();
        iAccountRepository.deposit(amount, SecurityUtils.getAuthenticatedUserId());
        AccountHistory accountHistory = new AccountHistory(account, TransactionType.deposit, amount);
        iAccountHistoryService.add(accountHistory);
        clearSession();
        return getBalanceByUserId();
    }
}
