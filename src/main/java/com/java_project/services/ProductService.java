package com.java_project.services;

import com.java_project.dto.Product.ProductCreateDTO;
import com.java_project.dto.Product.ProductItemDTO;

import java.util.List;

public interface ProductService {
    ProductItemDTO create(ProductCreateDTO model);
    List<ProductItemDTO> get();
}