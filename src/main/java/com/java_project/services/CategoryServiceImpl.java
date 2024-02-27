package com.java_project.services;

import lombok.AllArgsConstructor;

import com.java_project.dto.Category.CategoryItemDTO;
import com.java_project.entities.CategoryEntity;
import com.java_project.mapper.CategoryMapper;
import com.java_project.repositories.CategoryRepository;

import jakarta.persistence.TypedQuery;

import org.hibernate.mapping.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<CategoryItemDTO> getAll(Pageable pageable) {
        Page<CategoryEntity> categories = categoryRepository.findAll(pageable);
        return categories.map(categoryMapper::categoryItemDTO);
    }

    @Override
    public Page<CategoryItemDTO> searchByName(String name, Pageable pageable) {
        Page<CategoryEntity> categories = categoryRepository.findByNameContainingIgnoreCase(name, pageable);
        return categories.map(categoryMapper::categoryItemDTO);
    }
}