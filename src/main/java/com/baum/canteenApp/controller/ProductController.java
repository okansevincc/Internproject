package com.baum.canteenApp.controller;


import com.baum.canteenApp.repository.ProductRepository;
import com.baum.canteenApp.request.CreateProductRequest;
import com.baum.canteenApp.response.ProductResponse;
import com.baum.canteenApp.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Valid CreateProductRequest createProductRequest) {
        return new ResponseEntity<>(productService.create(createProductRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductByProductId(@PathVariable String id) {
        productService.deleteProductByProductId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/categoryId")
    public ResponseEntity<List<ProductResponse>> findProductByCategory_CategoryId(@RequestParam @NotBlank String categoryId) {
        return new ResponseEntity<>(productService.findProductByCategory_CategoryId(categoryId), HttpStatus.OK);
    }



    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> addUnitInStock
            (
                    @PathVariable @NotBlank String productId,
                    @RequestBody @NotNull Integer newUnitInStock
            ) {
        return new ResponseEntity<>(productService.addUnitInStock(productId, newUnitInStock), HttpStatus.OK);
    }


}
