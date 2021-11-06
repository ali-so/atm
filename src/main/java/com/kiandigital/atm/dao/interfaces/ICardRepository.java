package com.kiandigital.atm.dao.interfaces;

import com.kiandigital.atm.dao.IGeneralRepository;
import com.kiandigital.atm.model.Card;
import com.kiandigital.atm.model.User;

public interface ICardRepository extends IGeneralRepository<Card> {
    Card find(String cardNo);

    User findUserByCardNo(String cardNo);
}
