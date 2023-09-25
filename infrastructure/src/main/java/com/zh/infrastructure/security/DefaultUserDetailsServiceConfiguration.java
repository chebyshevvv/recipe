package com.zh.infrastructure.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class DefaultUserDetailsServiceConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public UserDetailsService defaultUserDetailsService(){
        return new DefaultUserDetailsServiceImpl();
    }
}
