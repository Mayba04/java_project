package com.java_project.services;

import com.java_project.dto.Product.ProductCreateDTO;
import com.java_project.dto.Product.ProductEditDTO;
import com.java_project.dto.Product.ProductItemDTO;
import com.java_project.dto.Product.ProductSearchResultDTO;

import java.util.List;

public interface ProductService {
    ProductItemDTO create(ProductCreateDTO model);
    List<ProductItemDTO> get();
    ProductSearchResultDTO searchProducts(String name, int categoryId,
    String description, int page, int size);
    ProductItemDTO edit(ProductEditDTO model);
    ProductItemDTO getById(Integer productId);
}