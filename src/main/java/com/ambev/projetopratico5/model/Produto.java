package com.ambev.projetopratico5.model;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double preco;

    public Produto(int id, String nome, String descricao, double preco) {
        this.id = id;
        setNome(nome);
        setDescricao(descricao);
        setPreco(preco);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio");
        }
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.length() < 10) {
            throw new IllegalArgumentException("A descrição deve ter pelo menos 10 caracteres");
        }
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 1) {
            throw new IllegalArgumentException("O preço deve ser pelo menos 1");
        }
        this.preco = preco;
    }
}