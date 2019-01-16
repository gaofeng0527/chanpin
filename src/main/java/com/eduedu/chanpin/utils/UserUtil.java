package com.eduedu.chanpin.utils;

/**
 * Created by Administrator on 2017/3/3.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserUtil {
    private static final Logger logger = LoggerFactory.getLogger(UserUtil.class.getName());

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            currentUser = (User) authentication.getPrincipal();
        } else if (authentication.getDetails() instanceof UserDetails) {
            currentUser = (User) authentication.getDetails();
        }
        return currentUser;
    }
}
