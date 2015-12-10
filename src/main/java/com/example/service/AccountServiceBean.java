package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Account;
import com.example.repository.AccountRepository;

@Service
@Transactional
public class AccountServiceBean implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    @Override
    public Account findByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        return account;
    }

}
