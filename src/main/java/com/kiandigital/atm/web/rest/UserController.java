package com.kiandigital.atm.web.rest;

import com.kiandigital.atm.model.User;
import com.kiandigital.atm.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/load/{username}")
    public User getUser(@PathVariable String username, @PathVariable String password) {
        return iUserService.loadUserByUsername(username);
    }
}
