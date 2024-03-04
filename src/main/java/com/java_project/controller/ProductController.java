package com.java_project.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.java_project.dto.Product.ProductCreateDTO;
import com.java_project.dto.Product.ProductEditDTO;
import com.java_project.dto.Product.ProductItemDTO;
import com.java_project.dto.Product.ProductSearchResultDTO;
import com.java_project.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@AllArgsConstructor
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public ResponseEntity<List<ProductItemDTO>> index() {
        return new ResponseEntity<>(productService.get(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductItemDTO> create(@Valid @ModelAttribute ProductCreateDTO model) {
        var result = productService.create(model);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ProductSearchResultDTO> searchProducts(
            @RequestParam (defaultValue = "")String name,
            @RequestParam (defaultValue = "0")int categoryId,
            @RequestParam (defaultValue = "")String description,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        ProductSearchResultDTO searchResult = productService.searchProducts(name, categoryId,
                description, page, size);

        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

     @GetMapping("/{productId}")
    public ResponseEntity<ProductItemDTO> getById(@PathVariable int productId) {
        var result = productService.getById(productId);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<ProductItemDTO> edit(@RequestBody ProductEditDTO model) {
        var result = productService.edit(model);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}