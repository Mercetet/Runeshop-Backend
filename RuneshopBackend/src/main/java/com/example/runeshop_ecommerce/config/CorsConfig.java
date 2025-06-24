package com.example.runeshop_ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://localhost:5173",
                        "http://localhost:5173",
                        "https://TU_ID_NGROK.ngrok-free.app",  // Backend expuesto via ngrok
                        "https://www.mercadopago.com.ar",
                        "https://www.mercadopago.com"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders(
                        "Authorization",
                        "Content-Type",
                        "Content-Disposition"
                )
                .allowCredentials(true)
                .maxAge(3600);
    }
}

