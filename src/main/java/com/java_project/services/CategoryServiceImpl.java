package com.java_project.services;

import lombok.AllArgsConstructor;
import com.java_project.dto.CategoryItemDTO;
import com.java_project.entities.CategoryEntity;
import com.java_project.mapper.CategoryMapper;
import com.java_project.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
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
}