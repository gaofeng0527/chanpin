package com.eduedu.chanpin.controller;

/**
 * Created by Administrator on 2017/2/28.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class.getName());

    /**
     * 登录页面
     * @return
     */
    @RequestMapping(ControllerConstants.URI_LOGIN_FORM)
    public String loginForm() {
        return ControllerConstants.VIEW_LOGIN_FORM;
    }

    /**
     * 登录成功
     * @return
     */
    @RequestMapping(ControllerConstants.URI_LOGIN_DEFAULT_TARGET)
    public String defaultLogin() {
        return ControllerConstants.VIEW_ADMIN_HOME;
    }

    /**
     * 登录失败
     * @param model
     * @return
     */
    @RequestMapping(ControllerConstants.URI_AUTH_FAILURE)
    public String authFailure(Model model) {
        model.addAttribute(ControllerConstants.MSG_ERROR, ControllerConstants.MSG_AUTH_FAILURE);

        return ControllerConstants.VIEW_LOGIN_FORM;
    }

    /**
     * 没有权限
     * @param model
     * @return
     */
    @RequestMapping(ControllerConstants.URI_ACCESS_DENIED)
    public String accessDenied(Model model) {
        model.addAttribute(ControllerConstants.MSG_ERROR, ControllerConstants.MSG_ACCESS_DENY);
        return ControllerConstants.VIEW_LOGIN_FORM;
    }

    /**
     * 登出操作
     * @param model
     * @return
     */
    @RequestMapping(ControllerConstants.URI_SUCCESS_LOGOUT)
    public String successLogout(Model model) {
        model.addAttribute(ControllerConstants.MSG_ERROR, ControllerConstants.MSG_LOGOUT_ALREADY);
        return ControllerConstants.VIEW_LOGIN_FORM;
    }


}
