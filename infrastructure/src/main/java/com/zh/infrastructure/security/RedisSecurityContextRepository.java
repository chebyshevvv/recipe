package com.zh.infrastructure.security;

import cn.hutool.core.util.SerializeUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class RedisSecurityContextRepository implements TokenMapSecurityContextRepository {
    private final StringRedisTemplate template;
    private final Integer token_expiration;

    public RedisSecurityContextRepository(StringRedisTemplate template, Integer tokenExpiration) {
        this.template = template;
        token_expiration = tokenExpiration;
    }

    /**
     * 不存在则返回null
     * */
    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {

        SecurityContext context = null;
        String token = TokenUtil.extractStringToken(requestResponseHolder.getRequest());
        String rawContext = this.getRaw(token);

        if (rawContext != null){

            context = SerializeUtil.deserialize(Base64.getDecoder().decode(rawContext));
            if (context.getAuthentication() == null){

                this.template.delete(token);
                context = null;
            }
        }

        return context;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

        String token = TokenUtil.extractStringToken(request);
        byte[] serialize = SerializeUtil.serialize(context);
        this.template.opsForValue().set(token, Base64.getEncoder().encodeToString(serialize),this.token_expiration, TimeUnit.HOURS);
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {

        String token = TokenUtil.extractStringToken(request);
        return Boolean.TRUE.equals(this.template.hasKey(token));
    }

    @Override
    public void saveContextByToken(SecurityContext context, String token) {

        this.template.opsForValue().set(token,Base64.getEncoder().encodeToString(SerializeUtil.serialize(context)),this.token_expiration, TimeUnit.HOURS);
    }

    @Override
    public SecurityContext getContextByToken(String token) {

        SecurityContext context = null;
        String rawContext = this.getRaw(token);

        if (rawContext != null){

            context = SerializeUtil.deserialize(Base64.getDecoder().decode(rawContext));
            if (context.getAuthentication() == null){

                this.template.delete(token);
                context = null;
            }
        }

        return context;
    }

    private String getRaw(String token){

        if (StrUtil.isBlank(token)){

            return null;
        }

        if (Boolean.TRUE.equals(this.template.hasKey(token))){

            return this.template.opsForValue().get(token);
        }else {

            return null;
        }
    }
}
