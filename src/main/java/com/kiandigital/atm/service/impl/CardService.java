package com.kiandigital.atm.service.impl;

import com.kiandigital.atm.dao.IGeneralRepository;
import com.kiandigital.atm.dao.interfaces.ICardRepository;
import com.kiandigital.atm.model.Card;
import com.kiandigital.atm.model.User;
import com.kiandigital.atm.service.GeneralService;
import com.kiandigital.atm.service.interfaces.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService extends GeneralService<Card> implements ICardService {

    @Autowired
    private ICardRepository iCardRepository;

    @Override
    protected IGeneralRepository<Card> getGeneralRepository() {
        return iCardRepository;
    }

    @Override
    public User findUserByCardNo(String cardNo) {
        return iCardRepository.findUserByCardNo(cardNo);
    }
}
