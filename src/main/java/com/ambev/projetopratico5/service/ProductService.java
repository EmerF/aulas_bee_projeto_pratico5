package com.ambev.projetopratico5.service;

import com.ambev.projetopratico5.dto.ProductDTO;
import com.ambev.projetopratico5.model.Product;
import com.ambev.projetopratico5.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product convertDTO(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    public ProductDTO convertProduct(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = convertDTO(productDTO);
        return convertProduct(productRepository.save(product));
    }

    public List<ProductDTO> searchByName(String name) {
        List<Product> productsWithName = productRepository.findByName(name);

        return productsWithName.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            updateProductDetails(product, productDTO);
            Product updatedProduct = productRepository.save(product);
            return convertProduct(updatedProduct);
        } else {
            return null;
        }
    }
    private void updateProductDetails(Product product, ProductDTO productDTO) {
        product.setName(productDTO.getName());
        product.setDescriptions(productDTO.getDescriptions());
        product.setPrice(productDTO.getPrice());
    }
    public boolean deleteProduct(String id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.map(product -> {
            productRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}

