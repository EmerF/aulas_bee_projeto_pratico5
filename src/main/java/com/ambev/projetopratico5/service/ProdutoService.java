package com.ambev.projetopratico5.service;


import com.ambev.projetopratico5.model.Produto;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {
    private List<Produto> produtos = new ArrayList<>();

    public void cadastrarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void atualizarProduto(String nome, Produto produtoAtualizado) {
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            if (produto.getNome().equals(nome)) {
                produtos.set(i, produtoAtualizado);
                return;
            }
        }
    }

    public Produto consultarPorNome(String nome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equals(nome)) {
                return produto;
            }
        }
        return null;
    }

    public void deletarPorNome(String nome) {
        produtos.removeIf(produto -> produto.getNome().equals(nome));
    }

    public MutablePropertyValues getProdutos() {
        return null;
    }

}