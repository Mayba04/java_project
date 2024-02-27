package com.java_project.services;


import lombok.AllArgsConstructor;
import com.java_project.dto.Product.ProductCreateDTO;
import com.java_project.dto.Product.ProductItemDTO;
import com.java_project.entities.CategoryEntity;
import com.java_project.entities.ProductEntity;
import com.java_project.entities.ProductImageEntity;
import com.java_project.repositories.ProductImageRepository;
import com.java_project.repositories.ProductRepository;
import com.java_project.storage.FileSaveFormat;
import com.java_project.storage.StorageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final StorageService storageService;
    @Override
    public ProductItemDTO create(ProductCreateDTO model) {
        var p = new ProductEntity();
        var cat = new CategoryEntity();
        cat.setId(model.getCategory_id());
        p.setName(model.getName());
        p.setDescription(model.getDescription());
        p.setPrice(model.getPrice());
        p.setDateCreated(LocalDateTime.now());
        p.setCategory(cat);
        p.setDelete(false);
        productRepository.save(p);
        int priority=1;
        for (var img : model.getFiles()) {
            try {
                var file = storageService.SaveImage(img, FileSaveFormat.WEBP);
                ProductImageEntity pi = new ProductImageEntity();
                pi.setName(file);
                pi.setDateCreated(LocalDateTime.now());
                pi.setPriority(priority);
                pi.setDelete(false);
                pi.setProduct(p);
                productImageRepository.save(pi);
            }
            catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            priority++;
        }
        return null;
    }

    @Override
    public List<ProductItemDTO> get() {
        var list = new ArrayList<ProductItemDTO>();
        var data = productRepository.findAll();
        for(var product : data) {
            ProductItemDTO productItemDTO = new ProductItemDTO();

            productItemDTO.setCategory( product.getCategory().getName() );
            productItemDTO.setId( product.getId() );
            productItemDTO.setName( product.getName() );
            productItemDTO.setPrice( product.getPrice() );
            productItemDTO.setDescription( product.getDescription() );

            var items = new ArrayList<String>();
            for (var img : product.getProductImages())
            {
                items.add(img.getName());
            }
            productItemDTO.setFiles(items);
            list.add(productItemDTO);
        }
        return list;
    }


}