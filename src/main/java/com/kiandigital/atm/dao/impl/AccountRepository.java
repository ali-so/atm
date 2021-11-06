package com.kiandigital.atm.dao.impl;

import com.kiandigital.atm.dao.GeneralRepository;
import com.kiandigital.atm.dao.interfaces.IAccountRepository;
import com.kiandigital.atm.model.Account;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class AccountRepository extends GeneralRepository<Account> implements IAccountRepository {

    @Override
    protected Class<Account> getDomainClass() {
        return Account.class;
    }

    @Override
    public Account loadAccountByUsername(String username) {
        String hql = " from " + domainClass.getName() + " e " +
                " where e.customer.username = :username ";
        Query<Account> query = getCurrentSession().createQuery(hql, domainClass);
        query.setParameter("username", username);
        return query.uniqueResult();
    }

    @Override
    public BigDecimal getBalanceByUserId(Long userId) {
        String hql = " from " + domainClass.getName() + " e " +
                " where e.customer.id = :userId ";
        Query<Account> query = getCurrentSession().createQuery(hql, domainClass);
        query.setParameter("userId", userId);
        return query.uniqueResult().getBalance();
    }

    @Override
    public int withdraw(BigDecimal amount, Long userId) {
        String hql = " update " + domainClass.getName() + " e " +
                "set e.balance = (e.balance - :amount) " +
                " where e.customer.id = :userId ";
        Query<Account> query = getCurrentSession().createQuery(hql);
        query.setParameter("amount", amount);
        query.setParameter("userId", userId);
        return query.executeUpdate();
    }

    @Override
    public int deposit(BigDecimal amount, Long userId) {
        String hql = " update " + domainClass.getName() + " e " +
                "set e.balance = (e.balance + :amount) " +
                " where e.customer.id = :userId ";
        Query<Account> query = getCurrentSession().createQuery(hql);
        query.setParameter("amount", amount);
        query.setParameter("userId", userId);
        return query.executeUpdate();
    }
}
