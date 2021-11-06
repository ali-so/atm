package com.kiandigital.atm.dao.impl;

import com.kiandigital.atm.dao.GeneralRepository;
import com.kiandigital.atm.dao.interfaces.IUserRepository;
import com.kiandigital.atm.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends GeneralRepository<User> implements IUserRepository {

    @Override
    protected Class<User> getDomainClass() {
        return User.class;
    }

    @Override
    public User loadUserByUsername(String username) {
        String hql = " from " + domainClass.getName() + " e " +
                " where e.username = '" + username + "'";
        Query<User> query = getCurrentSession().createQuery(hql, domainClass);

        return (User)query.uniqueResult();
    }

    @Override
    public void increaseFailedLoginAttempt(String username, boolean setDisabled) {
        String hql = "update " + domainClass.getName() + " e " +
                " set e.failedLoginAttempt = (e.failedLoginAttempt + 1) ";

        if(setDisabled) {
            hql += " ,e.enabled = 0 ";
        }
                hql += "where e.username = :username ";
        Query<User> query = getCurrentSession().createQuery(hql);
        query.setParameter("username", username);
        query.executeUpdate();
    }

    @Override
    public void resetFailedLoginAttempt(String username) {
        String hql = "update " + domainClass.getName() + " e " +
                " set e.failedLoginAttempt = 0 " +
                " where e.username = :username ";
        Query<User> query = getCurrentSession().createQuery(hql);
        query.setParameter("username", username);
        query.executeUpdate();
    }
}
