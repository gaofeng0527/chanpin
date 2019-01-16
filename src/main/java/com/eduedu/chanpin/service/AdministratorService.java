package com.eduedu.chanpin.service;

/**
 * Created by Administrator on 2017/3/1.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class AdministratorService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(AdministratorService.class.getName());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username == null || !username.equalsIgnoreCase("chanpinbu")) {
            return new User(username, "", false, false, false, false, null);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new User(username, "123456", true, true, true, true, authorities);
    }
}
