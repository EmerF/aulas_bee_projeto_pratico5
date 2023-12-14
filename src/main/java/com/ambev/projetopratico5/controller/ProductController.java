package com.ambev.projetopratico5.controller;

import com.ambev.projetopratico5.dto.ProductDTO;
import com.ambev.projetopratico5.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store/products")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDTO>> findProducts(@PathVariable String name) {
        List<ProductDTO> products = productService.searchByName(name);

        return products.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody @Valid
                                                    ProductDTO product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.saveProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id,
                                                    @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);

        return (updatedProduct != null) ?
                ResponseEntity.ok(updatedProduct) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        boolean deleted = productService.deleteProduct(id);

        return (deleted) ?
                ResponseEntity.ok("Successfully deleted") :
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Not found, try again with another Product!");
    }
}
