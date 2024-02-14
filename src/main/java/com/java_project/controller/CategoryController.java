package com.java_project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.java_project.entities.CategoryEntity;
import com.java_project.repositories.CategoryRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
     //final - як readlonly на С#
    private final CategoryRepository categoryRepository;
    
    @GetMapping
    public ResponseEntity<List<CategoryEntity>> index() {
        List<CategoryEntity> list = categoryRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    } 

    @PostMapping
    public ResponseEntity<CategoryEntity> addCategory(@RequestBody CategoryEntity category) {
        CategoryEntity savedCategory = categoryRepository.save(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
