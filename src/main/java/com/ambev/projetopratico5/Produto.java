package com.ambev.projetopratico5;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Produto {

    private String id;

    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @Size(min = 10, message = "A descrição deve ter no mínimo 10 caracteres")
    private String descricao;

    @Min(value = 1, message = "O preço deve ser no mínimo 1")
    private double preco;

    public Produto(String nome, String descricao, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    //Getters e setters:
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}

