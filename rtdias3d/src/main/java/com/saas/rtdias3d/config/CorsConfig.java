package com.saas.rtdias3d.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        String allowedOrigins = System.getenv().getOrDefault(
                "APP_CORS_ALLOWED_ORIGINS",
                "http://localhost:3000"
        );

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns(
                                Arrays.stream(allowedOrigins.split(","))
                                        .map(String::trim)
                                        .filter(origin -> !origin.isBlank())
                                        .toArray(String[]::new)
                        )
                        .allowedMethods(
                                "GET",
                                "POST",
                                "PUT",
                                "DELETE",
                                "OPTIONS"
                        )
                        .allowedHeaders("*");
            }
        };
    }
}