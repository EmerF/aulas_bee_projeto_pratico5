package com.ambev.projetopratico5.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ambev.projetopratico5.dto.ProdutoDTO;
import com.ambev.projetopratico5.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private final ProdutoService produtoService;
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ProdutoDTO cadastrarProduto(@RequestBody @Valid ProdutoDTO produto) {

        return produtoService.salvarProduto(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable String id, @RequestBody ProdutoDTO produtoDTO){
        ProdutoDTO produtoAtualizado = produtoService.atualizarProduto
                (id, produtoDTO);
        if(produtoAtualizado != null){
            return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/consultar/{nome}")
    public ResponseEntity<?> consultarPorNome(@PathVariable String nome) {
        List<ProdutoDTO> produtos = produtoService.consultarPorNome(nome);
        if (produtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        } else {
            return ResponseEntity.ok(produtos);
        }
    }

    @GetMapping("/error")
    public ResponseEntity errorProduct() {
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPorNome(@PathVariable String id){
        boolean deletado =  produtoService.deletarPorNome(id);
        if(deletado){
            return ResponseEntity.ok("Produto deletado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
    }
}