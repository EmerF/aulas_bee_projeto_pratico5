package com.ambev.projetopratico5.model;

import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class TipoProduto {

    @Id
    private String id;
    private String descricao;

// Getters e setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoProduto save(TipoProduto tipoProduto) {
        return null;
    }

    public Optional<TipoProduto> findById(String id2) {
        return null;
    }
}