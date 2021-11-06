package com.kiandigital.atm.service.impl;

import com.kiandigital.atm.dao.IGeneralRepository;
import com.kiandigital.atm.dao.interfaces.IAccountHistoryRepository;
import com.kiandigital.atm.model.AccountHistory;
import com.kiandigital.atm.service.GeneralService;
import com.kiandigital.atm.service.interfaces.IAccountHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountHistoryService extends GeneralService<AccountHistory> implements IAccountHistoryService {

    @Autowired
    private IAccountHistoryRepository iAccountHistoryRepository;

    @Override
    protected IGeneralRepository<AccountHistory> getGeneralRepository() {
        return iAccountHistoryRepository;
    }

}
