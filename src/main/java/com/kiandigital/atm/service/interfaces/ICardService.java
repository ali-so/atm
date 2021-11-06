package com.kiandigital.atm.service.interfaces;

import com.kiandigital.atm.model.Card;
import com.kiandigital.atm.model.User;
import com.kiandigital.atm.service.IGeneralService;

public interface ICardService extends IGeneralService<Card> {
    User findUserByCardNo(String cardNo);
}
