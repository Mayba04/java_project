package com.java_project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.java_project.entities.CategoryEntity;
import com.java_project.repositories.CategoryRepository;
import com.java_project.storage.StorageProperties;
import com.java_project.storage.StorageService;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    
@Bean
    CommandLineRunner runner(CategoryRepository repository, StorageService storageService) {
        return args -> {
            // storageService.init();

            // CategoryEntity category = new CategoryEntity();
            // category.setName("Одяг");
            // category.setImage("1.jpg");
            // category.setDescription("Для дорослих людей");
            // category.setCreationTime(LocalDateTime.now());
            // repository.save(category);
        };
    }
}

