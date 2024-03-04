package com.java_project.dto.Product;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductEditDTO {
    private int id;
    private String name;
    private double price;
    private String description;
    private int category_id;
    public List<ProductPhotoDTO> oldPhotos;
    public List<ProductPhotoDTO> newPhotos;
}