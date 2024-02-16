package com.java_project.mapper;

import com.java_project.dto.CategoryCreateDTO;
import com.java_project.dto.CategoryItemDTO;
import com.java_project.entities.CategoryEntity;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-16T19:26:23+0200",
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
}
