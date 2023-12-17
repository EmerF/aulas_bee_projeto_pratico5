package com.ambev.projetopratico5.controller;


import com.ambev.projetopratico5.model.Produto;
import com.ambev.projetopratico5.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        Produto produtoSalvo = produtoService.cadastrarProduto(produto);
        return new ResponseEntity<>(produtoSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable String id, @RequestBody Produto produto) {
        Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Produto> consultarProdutoPorNome(@PathVariable String nome) {
        Produto produto = produtoService.consultarProdutoPorNome(nome);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> deletarProdutoPorNome(@PathVariable String nome) {
        produtoService.deletarProdutoPorNome(nome);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}