package com.ambev.projetopratico5.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProdutoDTO {
    private String id;
    @NotBlank(message = "nome nao pode ser vazio")
    private String nome;

    @Size(min = 10, message = "tamanho minimo de 10 caracteres")
    private String descricao;

    @DecimalMin(value = "1", message = "valor minimo de 1")
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