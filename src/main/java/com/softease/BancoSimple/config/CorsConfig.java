package com.softease.BancoSimple.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.time.Duration;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        // Origen permitido
        config.setAllowedOrigins(List.of("http://localhost:5173", "https://frontend-ecommerce-react.vercel.app","https://react-bank-app-frontend.vercel.app" ));
        // Métodos HTTP permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Cabeceras permitidas
        config.setAllowedHeaders(List.of("*"));
        // Exponer estas cabeceras al cliente si las necesita
        config.setExposedHeaders(List.of("Authorization", "Content-Type"));
        // Permitir envío de cookies/credenciales
        config.setAllowCredentials(true);
        // Cachear respuesta de pre-flight durante 1 hora
        config.setMaxAge(Duration.ofHours(1));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplicar a todas las rutas
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
