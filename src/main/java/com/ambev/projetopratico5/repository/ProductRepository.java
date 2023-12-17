package com.ambev.projetopratico5.repository;

import com.ambev.projetopratico5.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String > {
    List<Product> findByName(String name);
}
