package com.kiandigital.atm.web.rest;

import com.kiandigital.atm.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private IAccountService iAccountService;

    @GetMapping("/balance")
    public BigDecimal getBalance() {
        return iAccountService.getBalanceByUserId();
    }

    @GetMapping("/withdraw")
    public String withdraw(BigDecimal amount) {
        return "Transaction is done successfully. your new balance is: " + iAccountService.withdraw(amount);
    }

    @GetMapping("/deposit")
    public String deposit(BigDecimal amount) {
        return "Transaction is done successfully. your new balance is: " + iAccountService.deposit(amount);
    }
}
