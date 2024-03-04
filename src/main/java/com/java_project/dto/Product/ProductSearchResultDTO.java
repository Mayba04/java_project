package com.java_project.dto.Product;

import lombok.Data;

import java.util.List;

@Data
public class ProductSearchResultDTO {
    private List<ProductItemDTO> list;
    private int totalCount;

    public ProductSearchResultDTO(List<ProductItemDTO> list, int totalCount) {
        this.list = list;
        this.totalCount = totalCount;
    }
}