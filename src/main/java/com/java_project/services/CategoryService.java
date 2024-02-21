package com.java_project.services;

import com.java_project.dto.CategoryItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryItemDTO> getAll(Pageable pageable);
}