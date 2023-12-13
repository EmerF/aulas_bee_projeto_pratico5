package com.ambev.projetopratico5.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Document
public class Produto {

    private String id;

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @Size(min = 10, max = 100, message = "Descrição deve ter entre 10 e 100 caracteres")
    private String descricao;

    @Min(value = 1, message = "Preço deve ser maior que zero")
    private double preco;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}