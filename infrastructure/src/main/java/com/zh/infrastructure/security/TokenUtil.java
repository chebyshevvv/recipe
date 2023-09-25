package com.zh.infrastructure.security;

import jakarta.servlet.http.HttpServletRequest;

public class TokenUtil {
    private static final String TOKEN_HEADER = "token";
    public static String extractStringToken(HttpServletRequest request){
        return request.getHeader("token");
    }
}
