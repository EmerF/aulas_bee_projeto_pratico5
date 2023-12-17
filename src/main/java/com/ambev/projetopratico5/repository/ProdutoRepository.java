package com.ambev.projetopratico5.repository;

import com.ambev.projetopratico5.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    List<Produto> findByName(String nome);
}