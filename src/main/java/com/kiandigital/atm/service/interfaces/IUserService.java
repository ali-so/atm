package com.kiandigital.atm.service.interfaces;

import com.kiandigital.atm.model.User;
import com.kiandigital.atm.service.IGeneralService;

public interface IUserService extends IGeneralService<User> {
    User loadUserByUsername(String username);

    void increaseFailedLoginAttempt(String username);

    void resetFailedLoginAttempt(String username);
}
