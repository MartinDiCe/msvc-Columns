package com.diceprojects.msvccolumns.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración de la aplicación web que implementa la interfaz WebMvcConfigurer.
 * Esta clase utiliza las anotaciones `@Configuration` y `@EnableWebMvc` para definir la configuración
 * específica del entorno web en la aplicación.
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

}
