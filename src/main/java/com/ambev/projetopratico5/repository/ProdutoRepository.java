package com.ambev.projetopratico5.repository;

import com.ambev.projetopratico5.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    Produto findByNome(String nome);
    void deleteByNome(String nome);
}