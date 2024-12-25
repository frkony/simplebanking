package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.Account;

import org.springframework.stereotype.Service;

// This class is a place holder you can change the complete implementation
@Service
public class AccountService {

    public Account findAccount(String str) {
        if (str == null) {
           return  new Account("", "");
        }
        Account account = new Account("", str);
        return account;
    }

}
