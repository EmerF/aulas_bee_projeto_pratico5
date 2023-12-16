package com.ambev.projetopratico5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto cadastrarProduto(Produto produto) {
        validarProduto(produto);
        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(String id, Produto produto) {
        validarProduto(produto);
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        produtoExistente.setNome(produto.getNome());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setPreco(produto.getPreco());

        return produtoRepository.save(produtoExistente);
    }

    public Produto consultarPorNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    public void deletarPorNome(String nome) {
        Produto produtoExistente = produtoRepository.findByNome(nome);
        if (produtoExistente != null) {
            produtoRepository.delete(produtoExistente);
        } else {
            throw new IllegalArgumentException("Produto não encontrado");
        }
    }

    private void validarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio");
        }
        if (produto.getDescricao() == null || produto.getDescricao().length() < 10) {
            throw new IllegalArgumentException("A descrição do produto deve ter no mínimo 10 caracteres");
        }
        if (produto.getPreco() < 1) {
            throw new IllegalArgumentException("O preço do produto deve ser no mínimo 1");
        }
    }
}

