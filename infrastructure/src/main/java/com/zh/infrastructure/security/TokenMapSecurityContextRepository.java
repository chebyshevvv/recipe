package com.zh.infrastructure.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.SecurityContextRepository;

public interface TokenMapSecurityContextRepository extends SecurityContextRepository {
    void saveContextByToken(SecurityContext context, String token);
    SecurityContext getContextByToken(String token);
}
