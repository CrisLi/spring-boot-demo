package com.example.service;

import com.example.model.Account;

public interface AccountService {

    public Account findOne(Long id);

    public Account findByUsername(String username);
}
