package com.java_project.controller;

import lombok.AllArgsConstructor;
import com.java_project.dto.CategoryCreateDTO;
import com.java_project.dto.CategoryEditDTO;
import com.java_project.dto.CategoryItemDTO;
import com.java_project.entities.CategoryEntity;
import com.java_project.mapper.CategoryMapper;
import com.java_project.repositories.CategoryRepository;
import com.java_project.storage.FileSaveFormat;
import com.java_project.storage.StorageService;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    //final - як readlonly на С#
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final StorageService storageService;

    @GetMapping
    public ResponseEntity<List<CategoryItemDTO>> index() {
        var model = categoryMapper.categoriesListItemDTO((categoryRepository.findAll()));
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryItemDTO> getCategoryById(@PathVariable Integer id) {
        try {
            CategoryEntity category = categoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            var result = categoryMapper.categoryItemDTO(category);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="/create")
    public ResponseEntity<CategoryEntity> addCategory(@RequestBody CategoryEntity category) {
        CategoryEntity savedCategory = categoryRepository.save(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    //     categoryRepository.deleteById(Math.toIntExact(id));
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            // Знаходимо категорію за id
            CategoryEntity category = categoryRepository.findById(id.intValue())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
    
            // Видаляємо пов'язані файли зображень
            storageService.delete(category.getImage());
    
            // Видаляємо саму категорію
            categoryRepository.deleteById(id.intValue());
    
            // Відправляємо відповідь, що все пройшло успішно
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IOException ex) {
            // Логуємо помилку видалення файлів (зображень)
            ex.printStackTrace(); // Логування стек-трейсу в консоль (замість цього можна використовувати логгер)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            // Логуємо інші винятки, наприклад, якщо категорія не знайдена
            ex.printStackTrace(); // Логування стек-трейсу в консоль (замість цього можна використовувати логгер)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CategoryItemDTO> create(@ModelAttribute CategoryCreateDTO dto) {
        try {
            var entity = categoryMapper.categoryEntityByCategoryCreateDTO(dto);
            entity.setCreationTime(LocalDateTime.now());
            String fileName = storageService.SaveImage(dto.getFile(), FileSaveFormat.WEBP);
            entity.setImage(fileName);
            categoryRepository.save(entity);
            var result = categoryMapper.categoryItemDTO(entity);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryItemDTO> updateCategory(
            @PathVariable Integer id,
            @RequestBody CategoryEditDTO dto) {
        try {
            CategoryEntity existingCategory = categoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            System.out.println("Before mapping: " + existingCategory);

            categoryMapper.updateCategoryEntityFromEditDTO(dto, existingCategory);

            System.out.println("After mapping: " + existingCategory);

            categoryRepository.save(existingCategory);

            var result = categoryMapper.categoryItemDTO(existingCategory);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
