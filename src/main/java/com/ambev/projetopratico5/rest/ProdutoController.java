package com.ambev.projetopratico5.rest;


import com.ambev.projetopratico5.dto.ProdutoDTO;
import com.ambev.projetopratico5.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @PostMapping
    public ProdutoDTO cadastrarProduto(@RequestBody @Valid ProdutoDTO produto) {

        return produtoService.salvarProduto(produto);
    }

    @GetMapping("/consultar/{nome}")
    public ResponseEntity<List<ProdutoDTO>> consultarProdutos(@PathVariable String nome) {
        List<ProdutoDTO> produtos = produtoService.consultarPorNome(nome);
        if (CollectionUtils.isEmpty(produtos)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(produtos);
        }
    }
    @GetMapping("/error")
    public ResponseEntity errorProduct() {
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable String id,
                                                       @RequestBody ProdutoDTO produtoDTO){
        ProdutoDTO produtoAtualizado = produtoService.atualizarProduto
                (id, produtoDTO);
        if(produtoAtualizado != null){
            return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable String id){
        boolean deletado =  produtoService.deletarProduto(id);
        if(deletado){
            return ResponseEntity.ok("Produto deletado");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
    }
}
