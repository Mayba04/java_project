package com.java_project.services;

import com.java_project.dto.CategoryItemDTO;
import com.java_project.entities.CategoryEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryItemDTO> getAll(Pageable pageable);
    Page<CategoryEntity> search(String keyword, int page, int size);
}