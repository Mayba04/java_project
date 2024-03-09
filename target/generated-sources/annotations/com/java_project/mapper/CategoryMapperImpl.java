package com.java_project.mapper;

import com.java_project.dto.Category.CategoryCreateDTO;
import com.java_project.dto.Category.CategoryEditDTO;
import com.java_project.dto.Category.CategoryItemDTO;
import com.java_project.dto.common.SelectItemDTO;
import com.java_project.entities.CategoryEntity;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-08T19:24:03+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    private final DateTimeFormatter dateTimeFormatter_dd_MM_yyyy_HH_MM_ss_11366998336 = DateTimeFormatter.ofPattern( "dd.MM.yyyy HH:MM:ss" );

    @Override
    public CategoryItemDTO categoryItemDTO(CategoryEntity category) {
        if ( category == null ) {
            return null;
        }

        CategoryItemDTO categoryItemDTO = new CategoryItemDTO();

        if ( category.getCreationTime() != null ) {
            categoryItemDTO.setDateCreated( dateTimeFormatter_dd_MM_yyyy_HH_MM_ss_11366998336.format( category.getCreationTime() ) );
        }
        categoryItemDTO.setId( category.getId() );
        categoryItemDTO.setName( category.getName() );
        categoryItemDTO.setImage( category.getImage() );
        categoryItemDTO.setDescription( category.getDescription() );

        return categoryItemDTO;
    }

    @Override
    public List<CategoryItemDTO> categoriesListItemDTO(List<CategoryEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<CategoryItemDTO> list1 = new ArrayList<CategoryItemDTO>( list.size() );
        for ( CategoryEntity categoryEntity : list ) {
            list1.add( categoryItemDTO( categoryEntity ) );
        }

        return list1;
    }

    @Override
    public CategoryEntity categoryEntityByCategoryCreateDTO(CategoryCreateDTO category) {
        if ( category == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName( category.getName() );
        categoryEntity.setDescription( category.getDescription() );

        return categoryEntity;
    }

    @Override
    public CategoryEntity categoryEditDto(CategoryEditDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId( dto.getId() );
        categoryEntity.setName( dto.getName() );
        categoryEntity.setDescription( dto.getDescription() );

        return categoryEntity;
    }

    @Override
    public SelectItemDTO selectItemCategory(CategoryEntity category) {
        if ( category == null ) {
            return null;
        }

        SelectItemDTO selectItemDTO = new SelectItemDTO();

        selectItemDTO.setId( category.getId() );
        selectItemDTO.setName( category.getName() );

        return selectItemDTO;
    }

    @Override
    public List<SelectItemDTO> listSelectItemCategory(List<CategoryEntity> categories) {
        if ( categories == null ) {
            return null;
        }

        List<SelectItemDTO> list = new ArrayList<SelectItemDTO>( categories.size() );
        for ( CategoryEntity categoryEntity : categories ) {
            list.add( selectItemCategory( categoryEntity ) );
        }

        return list;
    }
}
