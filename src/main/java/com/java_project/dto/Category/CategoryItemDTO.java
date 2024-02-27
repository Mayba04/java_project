package com.java_project.dto.Category;

import lombok.Data;

@Data
public class CategoryItemDTO {
    private int id;
    private String name;
    private String image;
    private String description;
    private String dateCreated;
}
