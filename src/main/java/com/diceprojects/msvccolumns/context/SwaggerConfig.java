package com.diceprojects.msvccolumns.context;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Swagger para la generación de documentación de la API.
 * Esta clase define un bean que proporciona la configuración personalizada para la generación de la documentación de Swagger.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Define y configura la instancia de OpenAPI para la documentación de la API.
     *
     * @return Una instancia de OpenAPI configurada con información sobre la API.
     */
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