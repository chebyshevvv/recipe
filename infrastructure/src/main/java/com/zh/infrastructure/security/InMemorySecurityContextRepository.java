package com.zh.infrastructure.security;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemorySecurityContextRepository implements TokenMapSecurityContextRepository {
    private final SecurityContextMap securityContextMap = new SecurityContextMap();

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        SecurityContext context = null;
        String token = TokenUtil.extractStringToken(requestResponseHolder.getRequest());

        if (!securityContextMap.containsKey(token)){
            context = SecurityContextHolder.createEmptyContext();
        }
        else if (securityContextMap.get(token).getAuthentication() == null){
            securityContextMap.remove(token);
            context = SecurityContextHolder.createEmptyContext();
        }else {
            context = securityContextMap.get(token);
        }
        return context;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        String token = TokenUtil.extractStringToken(request);
        securityContextMap.put(token,context);
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        String token = TokenUtil.extractStringToken(request);
        return this.securityContextMap.containsKey(token);
    }
    @Override
    public void saveContextByToken(SecurityContext context,String token){
        securityContextMap.put(token,context);
    }

    @Override
    public SecurityContext getContextByToken(String token) {
        return securityContextMap.get(token);
    }

    public static class SecurityContextMap{
        private final Map<String,SecurityContext> securityContextMap = new ConcurrentHashMap<>(16);
        public void put(String key,SecurityContext context){
            if (!StrUtil.isBlank(key)){
                securityContextMap.put(key,context);
            }
        }
        public SecurityContext get(String key){
            if (!StrUtil.isBlank(key)){
                return securityContextMap.get(key);
            }
            return null;
        }
        public void remove(String key){
            if (!StrUtil.isBlank(key)){
                securityContextMap.remove(key);
            }
        }
        public boolean containsKey(String key){
            if (StrUtil.isBlank(key)){
                return false;
            }
            return securityContextMap.containsKey(key);
        }
    }
}
