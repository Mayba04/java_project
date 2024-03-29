package com.java_project.repositories;


import com.java_project.entities.CategoryEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    Page<CategoryEntity> findByName(String keyword, Pageable pageable);

    Page<CategoryEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}