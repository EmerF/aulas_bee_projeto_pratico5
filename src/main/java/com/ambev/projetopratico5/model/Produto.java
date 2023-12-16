package com.ambev.projetopratico5.model;


import org.springframework.data.annotation.Id;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Produto {

    @Id
    private String id;

    private String nome;

    private String descricao;

    private double preco;

    public String getId() { return id; }

    public void setId(String id){
        this.id = id;
    }

    public String getNome(){ return nome;}

    public void setNome(String nome){
        this.nome=nome;
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
