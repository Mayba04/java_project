package com.java_project.configuration;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.java_project.storage.StorageProperties;


@Configuration
@AllArgsConstructor
public class CorsConfig implements WebMvcConfigurer {
    private final StorageProperties storageProperties;


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/")
                .allowedOrigins("http://localhost:5173") // Add the appropriate origin of your client application
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/"+storageProperties.getLocation()+"/**")
                .addResourceLocations("file:"+storageProperties.getLocation()+"/");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}