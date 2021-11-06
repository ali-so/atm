package com.kiandigital.atm.dao.impl;

import com.kiandigital.atm.dao.GeneralRepository;
import com.kiandigital.atm.dao.interfaces.IAccountHistoryRepository;
import com.kiandigital.atm.model.AccountHistory;
import org.springframework.stereotype.Repository;

@Repository
public class AccountHistoryRepository extends GeneralRepository<AccountHistory> implements IAccountHistoryRepository {

    @Override
    protected Class<AccountHistory> getDomainClass() {
        return AccountHistory.class;
    }
}
