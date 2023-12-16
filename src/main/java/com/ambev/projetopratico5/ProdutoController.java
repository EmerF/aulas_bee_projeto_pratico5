package com.ambev.projetopratico5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> cadastrarProduto(@RequestBody Produto produto) {
        try {
            Produto produtoCadastrado = produtoService.cadastrarProduto(produto);
            return new ResponseEntity<>(produtoCadastrado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable String id, @RequestBody Produto produto) {
        try {
            Produto produtoAtualizado = produtoService.atualizarProduto(id, produto);
            return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> consultarPorNome(@PathVariable String nome) {
        Produto produtoEncontrado = produtoService.consultarPorNome(nome);
        if (produtoEncontrado != null) {
            return new ResponseEntity<>(produtoEncontrado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/nome/{nome}")
    public ResponseEntity<Void> deletarPorNome(@PathVariable String nome) {
        produtoService.deletarPorNome(nome);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
