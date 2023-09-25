package com.zh.infrastructure.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
    	return new OpenAPI()
                .info(new Info().title("接口文檔").description("接口文檔").version("1.0"))
                .externalDocs(new ExternalDocumentation())
                .components(new Components().addSecuritySchemes("token",new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name("token"))
                )
                .addSecurityItem(new SecurityRequirement().addList("token"));
    }

}