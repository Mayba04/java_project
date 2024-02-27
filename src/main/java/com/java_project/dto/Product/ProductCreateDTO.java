package com.java_project.dto.Product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductCreateDTO {
    private String name;
    private double price;
    private String description;
    private int category_id;
    private List<MultipartFile> files;
}