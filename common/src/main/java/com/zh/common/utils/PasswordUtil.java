package com.zh.common.utils;

import java.util.regex.Pattern;

public class PasswordUtil {
    /**
     * 包含字母、数字和特殊字符并且长度为8至16位
     * */
    public static final String PATTERN = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[^\\da-zA-Z\\s]).{8,16}$";
    public static Boolean match(String password){
        return Pattern.matches(PATTERN,password);
    }
}
