package com.kiandigital.atm.service;

public interface IGeneralService<T> {
    void flushSession();

    void clearSession();

    void add(T entity);
}
