package com.kiandigital.atm.dao.impl;

import com.kiandigital.atm.dao.GeneralRepository;
import com.kiandigital.atm.dao.interfaces.ICardRepository;
import com.kiandigital.atm.model.Card;
import com.kiandigital.atm.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CardRepository extends GeneralRepository<Card> implements ICardRepository {

    @Override
    protected Class<Card> getDomainClass() {
        return Card.class;
    }

    @Override
    public Card find(String cardNo) {
        String hql = " from " + domainClass.getName() + " e " +
                " where e.cardNo = '" + cardNo + "'";
        Query<Card> query = getCurrentSession().createQuery(hql, domainClass);

        return query.getSingleResult();
    }

    public User findUserByCardNo(String cardNo) {
        String hql = " select e.account.customer from " + domainClass.getName() + " e " +
                " where e.cardNo = :cardNo ";
        Query<User> query = getCurrentSession().createQuery(hql, User.class);
        query.setParameter("cardNo", cardNo);
        return query.uniqueResult();
    }

}
