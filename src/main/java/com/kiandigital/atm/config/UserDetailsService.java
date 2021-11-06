package com.kiandigital.atm.config;

import com.kiandigital.atm.common.exceptions.ApplicationException;
import com.kiandigital.atm.model.User;
import com.kiandigital.atm.service.interfaces.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private ICardService iCardService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        String lowerCaseUsername = username.toLowerCase(Locale.ENGLISH);
            User user = iCardService.findUserByCardNo(lowerCaseUsername);

            user.setUsername(username);

            if (user == null) {
                throw new ApplicationException("Invalid username or password");
            } else {
                if (!user.isEnabled()) {
                    throw new DisabledException("User " + lowerCaseUsername + " is disabled!");
                }
            }
            return UserPrincipal.create(user);
    }
}
