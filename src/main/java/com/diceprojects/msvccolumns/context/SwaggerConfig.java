package com.diceprojects.msvccolumns.context;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI CustomOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("API Documentation")
                        .version("v0.0.1")
                        .description("Documentation msvc-columns")
                        .termsOfService("https://diceprojects.com/termOfService")
                        .license(new License().name("diceLicense").url("https://diceprojects/api/license")));

    }

}