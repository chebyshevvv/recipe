package com.zh.infrastructure.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import java.util.Objects;


@Configuration
@EnableWebSecurity
@Slf4j
//开启方法权限校验
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final UserDetailsService service;
    private final PasswordEncoder encoder;
    private final StringRedisTemplate template;
    private final Integer tokenExpiration;

    public SecurityConfig(UserDetailsService service, PasswordEncoder encoder, StringRedisTemplate template, @Value("${infrastructure.security.token-expiration-hours}") Integer tokenExpiration) {
        this.service = service;
        this.encoder = encoder;
        this.template = template;
        this.tokenExpiration = tokenExpiration;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors(Customizer.withDefaults())
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(HttpMethod.OPTIONS,"/*").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/swagger-ui/*").permitAll()
                                .requestMatchers("/captcha.jpg").permitAll()
                                .requestMatchers("/v3/api-docs").permitAll()
                                .requestMatchers("/doc.html").permitAll()
                                .requestMatchers("/webjars/*/*").permitAll()
                                .requestMatchers("/favicon.ico").permitAll()
                                .requestMatchers("/v3/api-docs/*").permitAll()
                                .requestMatchers("/stu/course/like").permitAll()
                                .requestMatchers("/study").permitAll()
                                .requestMatchers("/studio/page").permitAll()
                                .requestMatchers("/studio/getById").permitAll()
                                .requestMatchers("/studio/*/page").permitAll()
                                .requestMatchers("/studio/*/getById").permitAll()
                                .requestMatchers("/studio/*/*/page").permitAll()
                                .anyRequest().permitAll()
                )
                .logout(logout -> logout.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()))
                .securityContext(context -> context.securityContextRepository(securityContextRepository()));
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
        provider.setPasswordEncoder(encoder);
        return new ProviderManager(provider);
    }
    @Bean
    public TokenMapSecurityContextRepository securityContextRepository(){
        TokenMapSecurityContextRepository repository = null;
        try {

            String pong = Objects.requireNonNull(template.getConnectionFactory()).getConnection().ping();
            if ("PONG".equalsIgnoreCase(pong)){

                repository = new RedisSecurityContextRepository(template, tokenExpiration);
                log.info("Redis连接成功,将使用Redis存储认证信息");
            }else {

                repository = new InMemorySecurityContextRepository();
                log.error("Redis连接失败,将使用Memory存储认证信息");
            }

        }catch (RedisConnectionFailureException e){

            repository = new InMemorySecurityContextRepository();
            log.error("Redis连接失败,将使用Memory存储认证信息");
        }
        return repository;
    }
}
