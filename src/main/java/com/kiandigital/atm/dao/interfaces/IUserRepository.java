package com.kiandigital.atm.dao.interfaces;

import com.kiandigital.atm.dao.IGeneralRepository;
import com.kiandigital.atm.model.User;

public interface IUserRepository extends IGeneralRepository<User> {
    User loadUserByUsername(String username);

    void increaseFailedLoginAttempt(String username, boolean setDisabled);

    void resetFailedLoginAttempt(String username);
}
