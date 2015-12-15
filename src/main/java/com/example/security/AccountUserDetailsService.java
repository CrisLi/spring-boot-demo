package com.example.security;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Account;
import com.example.service.AccountService;

@Service
public class AccountUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {

            Account account = accountService.findByUsername(username);

            Collection<GrantedAuthority> authorities = createAuthorityList("ROLE_" + account.getRole().toString());

            return new User(account.getUsername(), account.getPassword(), authorities);

        } catch (ResourceNotFoundException e) {
            throw new UsernameNotFoundException("Username \"" + username + "\" is not found.");
        }

    }

}
