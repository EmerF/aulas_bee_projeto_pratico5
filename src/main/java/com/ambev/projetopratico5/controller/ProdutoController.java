package com.ambev.projetopratico5.controller;

import com.ambev.projetopratico5.model.Produto;
import com.ambev.projetopratico5.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public void cadastrarProduto(@RequestBody Produto produto) {
        produtoService.cadastrarProduto(produto);
    }

    @PutMapping("/{nome}")
    public void atualizarProduto(@PathVariable String nome, @RequestBody Produto produtoAtualizado) {
        produtoService.atualizarProduto(nome, produtoAtualizado);
    }

    @GetMapping("/{nome}")
    public Produto consultarPorNome(@PathVariable String nome) {
        return produtoService.consultarPorNome(nome);
    }

    @DeleteMapping("/{nome}")
    public void deletarPorNome(@PathVariable String nome) {
        produtoService.deletarPorNome(nome);
    }
}