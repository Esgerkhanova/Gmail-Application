package com.internintelligence.gmailapplication.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gmail & Google Translate Clone API")
                        .description("5-page clone API for Gmail and Google Translate")
                        .version("1.0.0"));
    }
}
