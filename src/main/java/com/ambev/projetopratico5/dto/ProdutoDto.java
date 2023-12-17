package com.ambev.projetopratico5.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProdutoDto {

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @Size(min = 10, message = "Descrição deve ter no mínimo 10 caractres")
    private String descricao;

    @Min(value = 0, message = "Preço deve ser maior ou igual a zero")
    private double preco;

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