package com.ambev.projetopratico5.DTO;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProdutoDTO {

    private String id;

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @Size(min = 10, message = "Tamanho mínimo de 10 caracteres")
    private String descricao;

    @DecimalMin(value = "1", message = "Valor mínimo de 1")
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
