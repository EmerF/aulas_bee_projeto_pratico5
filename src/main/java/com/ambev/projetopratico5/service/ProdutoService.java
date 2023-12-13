package com.ambev.projetopratico5.service;

import com.ambev.projetopratico5.model.Produto;
import com.ambev.projetopratico5.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(String id, Produto produto) {
        produto.setId(id);
        return produtoRepository.save(produto);
    }

    public Produto consultarProdutoPorNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    public void deletarProdutoPorNome(String nome) {
        Produto produto = produtoRepository.findByNome(nome);
        produtoRepository.delete(produto);
    }
}