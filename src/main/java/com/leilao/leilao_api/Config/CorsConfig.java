package com.leilao.leilao_api.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration //Avisando que é uma classe de configuração, que carrega ao iniciar
public class CorsConfig implements WebMvcConfigurer {
    
    
    public void addCorsMapping(CorsRegistry registry) {
        registry.addMapping("/api/**").allowedOrigins("https://localhost:5173").allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
