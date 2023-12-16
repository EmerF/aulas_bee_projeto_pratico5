package com.ambev.projetopratico5.repository;

import com.ambev.projetopratico5.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {
    List<Produto> findByNome(String nome);


}
