package com.kiandigital.atm.service.impl;

import com.kiandigital.atm.common.exceptions.ApplicationException;
import com.kiandigital.atm.dao.IGeneralRepository;
import com.kiandigital.atm.dao.interfaces.IUserRepository;
import com.kiandigital.atm.model.User;
import com.kiandigital.atm.service.GeneralService;
import com.kiandigital.atm.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends GeneralService<User> implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    protected IGeneralRepository<User> getGeneralRepository() {
        return iUserRepository;
    }

    @Override
    public User loadUserByUsername(String username) {
        return iUserRepository.loadUserByUsername(username);
    }

    @Override
    @Transactional
    public void increaseFailedLoginAttempt(String username) {
        User user = loadUserByUsername(username);
        if(user == null) {
            throw new ApplicationException("Invalid username or password!");
        }

        if (user.getFailedLoginAttempt() == 2) {
            iUserRepository.increaseFailedLoginAttempt(username, true);
        } else {
            iUserRepository.increaseFailedLoginAttempt(username, false);
        }

    }

    @Override
    @Transactional
    public void resetFailedLoginAttempt(String username) {
        iUserRepository.resetFailedLoginAttempt(username);
    }
}
