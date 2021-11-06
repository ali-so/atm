package com.kiandigital.atm.service;

import com.kiandigital.atm.dao.IGeneralRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public abstract class GeneralService<T> implements IGeneralService<T> {
    protected abstract IGeneralRepository<T> getGeneralRepository();

    @Override
    public void flushSession() {
        getGeneralRepository().flushSession();
    }

    @Override
    public void clearSession() {
        getGeneralRepository().clearSession();
    }

    @Override
    @Transactional
    public void add(T entity) {
        getGeneralRepository().add(entity);
    }
}
