package com.kiandigital.atm.dao;

public interface IGeneralRepository<T> {
    void add(T entity);

    void flushSession();

    void clearSession();
}
