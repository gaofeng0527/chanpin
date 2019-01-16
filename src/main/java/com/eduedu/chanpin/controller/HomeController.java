package com.eduedu.chanpin.controller;

/**
 * Created by Administrator on 2017/2/28.
 */

import com.eduedu.chanpin.security.SecurityContextHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class.getName());

    /**
     * 访问入口
     *
     * @return
     */
    @RequestMapping(ControllerConstants.URI_HOME)
    public String home() {
        User user = SecurityContextHelper.getUser();
        if(null == user){
            return ControllerConstants.URI_REDIRECT + ControllerConstants.URI_LOGIN_FORM;
        }
        return ControllerConstants.VIEW_ADMIN_HOME;
    }

}
