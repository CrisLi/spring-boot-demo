package com.example.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.model.Account;
import com.example.service.AccountService;

@Service
public class AccountUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountService.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("Username \"" + username + "\" is not found.");
        }

        Collection<GrantedAuthority> authorities = Collections
                .singleton(new SimpleGrantedAuthority("ROLE_" + account.getRole().toString()));

        return new User(account.getUsername(), account.getPassword(), authorities);
    }

}
