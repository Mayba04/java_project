package com.java_project;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.github.javafaker.Faker;
import com.java_project.entities.CategoryEntity;
import com.java_project.repositories.CategoryRepository;
import com.java_project.storage.FileSaveFormat;
import com.java_project.storage.StorageProperties;
import com.java_project.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

     @Bean
    CommandLineRunner runner(CategoryRepository repository, StorageService storageService, RestTemplate restTemplate) {
        return args -> {
            if (repository.count() == 0) {
                Faker faker = new Faker();

                for (int i = 0; i < 5; i++) { // Додайте кілька категорій (змініть кількість за потребою)
                    CategoryEntity category = new CategoryEntity();
                    category.setName(faker.commerce().department());
                    category.setDescription(faker.lorem().sentence());
                    category.setCreationTime(LocalDateTime.now());
                    
                    // Зображення з https://picsum.photos/200/300
                    String imageUrl = "https://picsum.photos/200/300";
                    String storedFilename = storageService.saveImageFromUrl(imageUrl, FileSaveFormat.WEBP);
                    category.setImage(storedFilename);

                    repository.save(category);
                }
            }
        };
    }
    
// @Bean
//     CommandLineRunner runner(CategoryRepository repository, StorageService storageService) {
//         return args -> {
//             // storageService.init();

//             // CategoryEntity category = new CategoryEntity();
//             // category.setName("Одяг");
//             // category.setImage("1.jpg");
//             // category.setDescription("Для дорослих людей");
//             // category.setCreationTime(LocalDateTime.now());
//             // repository.save(category);
//         };
//     }
 }

