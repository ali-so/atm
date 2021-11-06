package com.kiandigital.atm.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public abstract class GeneralRepository<T> implements IGeneralRepository<T> {
    protected Class<T> domainClass = getDomainClass();

    protected abstract Class<T> getDomainClass();

    @Autowired
    protected EntityManager entityManager;

    protected Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public void add(T entity) {
        Session session = getCurrentSession();
        session.save(entity);
    }

    @Override
    public void flushSession() {
        getCurrentSession().flush();
    }

    @Override
    public void clearSession() {
        getCurrentSession().clear();
    }
}
