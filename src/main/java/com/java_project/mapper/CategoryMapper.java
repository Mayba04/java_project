package com.java_project.mapper;

import com.java_project.dto.CategoryCreateDTO;
import com.java_project.dto.CategoryEditDTO;
import com.java_project.dto.CategoryItemDTO;
import com.java_project.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(source = "creationTime", target = "dateCreated", dateFormat = "dd.MM.yyyy HH:MM:ss")
    CategoryItemDTO categoryItemDTO(CategoryEntity category);
    List<CategoryItemDTO> categoriesListItemDTO(List<CategoryEntity> list);

    @Mapping(target = "image", ignore = true)
    CategoryEntity categoryEntityByCategoryCreateDTO(CategoryCreateDTO category);

    default CategoryEntity updateCategoryEntityFromEditDTO(CategoryEditDTO editDTO, @MappingTarget CategoryEntity entity) {
        if (editDTO == null) {
            return null;
        }
        entity.setName(editDTO.getName());
        entity.setDescription(editDTO.getDescription());
    
        return entity;
    }

    @Mapping(target = "image", ignore = true)
    CategoryEntity categoryEditDto(CategoryEditDTO dto);

}