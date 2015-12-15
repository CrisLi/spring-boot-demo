package com.example.service;

import static com.example.config.CacheConfig.ACCOUNTS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Account;
import com.example.repository.AccountRepository;

@Service
@Transactional
@CacheConfig(cacheNames = ACCOUNTS)
public class AccountServiceBean implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional(readOnly = true)
    @Cacheable(key = "#id")
    @Override
    public Account findOne(Long id) {
        Account account = accountRepository.findOne(id);
        if (account == null) {
            throw new ResourceNotFoundException(Account.class);
        }
        return account;
    }

    @Transactional(readOnly = true)
    @Cacheable(key = "#username")
    @Override
    public Account findByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new ResourceNotFoundException(Account.class);
        }
        return account;
    }

}
