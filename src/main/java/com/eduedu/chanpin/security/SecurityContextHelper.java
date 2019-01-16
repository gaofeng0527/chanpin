package com.eduedu.chanpin.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityContextHelper {
	public static Map<String,HttpSession> sessionMap=new HashMap<String,HttpSession>();
	public static Authentication getAuthentication(){
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public static User getUser(){
		Authentication auth=getAuthentication();
		if(auth!=null){
			Object details=auth.getDetails();
			if(details instanceof User){
				return (User)details;
			}
		}
		return null;
	}

}
