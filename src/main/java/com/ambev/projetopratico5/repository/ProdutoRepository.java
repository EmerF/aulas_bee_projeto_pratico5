package com.ambev.projetopratico5.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ambev.projetopratico5.model.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
    List<Produto> findByNome(String nome);
    Produto findUmProdutoByNome(String nome);
    // Você pode adicionar métodos personalizados de consulta, se necessário
}