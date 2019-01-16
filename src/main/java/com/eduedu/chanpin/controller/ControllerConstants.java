package com.eduedu.chanpin.controller;

public interface ControllerConstants {

    String URI_REDIRECT = "redirect:";

    String URI_HOME = "";
    String URI_LOGIN_FORM = URI_HOME + "/loginForm";
    String URI_AUTH_FAILURE = URI_HOME + "/authFailure";
    String URI_ACCESS_DENIED = URI_HOME + "/accessDenied";
    String URI_LOGIN_DEFAULT_TARGET = URI_HOME + "/defaultTarget";
    String URI_SUCCESS_LOGOUT = URI_HOME + "/successLogout";

    String VIEW_LOGIN_FORM = "loginForm.htm";//登录页面
    String VIEW_ADMIN_HOME = "index.html";//后台首页

    String MSG_AUTH_FAILURE = "信息不匹配，请重新输入！";
    String MSG_ACCESS_DENY = "无权访问，请另行输入！";
    String MSG_LOGOUT_ALREADY = "已退出，请登录！";

    String MSG_ERROR = "error";

    //科目
    String URI_ADMIN_SUBJECT = URI_HOME + "/admin/subjects";
    String VIEW_SUBJECTS = URI_HOME + "/page/subject/subjectList.html";


}
