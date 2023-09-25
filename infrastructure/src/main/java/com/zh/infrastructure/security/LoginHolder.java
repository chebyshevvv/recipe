package com.zh.infrastructure.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginHolder {
    public static String getUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails){
            return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        }else {
            return "admin";
        }
    }
    public static Object getLoginUser(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
