package com.ambev.projetopratico5;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProdutoServiceIntegrationTest {

    @Autowired
    private ProdutoService produtoService;

    @Test
    public void testCadastrarProduto() {
        Produto produto = new Produto("1", "Produto A", "Descrição do Produto A", 10.0);
        Produto produtoCadastrado = produtoService.cadastrarProduto(produto);

        assertNotNull(produtoCadastrado.getId());
        assertEquals("Produto A", produtoCadastrado.getNome());
        assertEquals("Descrição do Produto A", produtoCadastrado.getDescricao());
        assertEquals(10.0, produtoCadastrado.getPreco());
    }

    @Test
    public void testAtualizarProduto() {
        Produto produtoExistente = produtoService.cadastrarProduto(new Produto("2", "Produto B", "Descrição do Produto B", 20.0));

        produtoExistente.setNome("Produto B Atualizado");
        produtoExistente.setDescricao("Descrição Atualizada");
        produtoExistente.setPreco(25.0);

        Produto produtoAtualizado = produtoService.atualizarProduto(produtoExistente.getId(), produtoExistente);

        assertEquals("Produto B Atualizado", produtoAtualizado.getNome());
        assertEquals("Descrição Atualizada", produtoAtualizado.getDescricao());
        assertEquals(25.0, produtoAtualizado.getPreco());
    }

    @Test
    public void testConsultarPorNome() {
        Produto produto = new Produto("3", "Produto C", "Descrição do Produto C", 30.0);
        produtoService.cadastrarProduto(produto);

        Produto produtoConsultado = produtoService.consultarPorNome("Produto C");

        assertNotNull(produtoConsultado);
        assertEquals("Produto C", produtoConsultado.getNome());
        assertEquals("Descrição do Produto C", produtoConsultado.getDescricao());
        assertEquals(30.0, produtoConsultado.getPreco());
    }

    @Test
    public void testDeletarPorNome() {
        Produto produto = new Produto("4", "Produto D", "Descrição do Produto D", 40.0);
        produtoService.cadastrarProduto(produto);

        produtoService.deletarPorNome("Produto D");

        Produto produtoDeletado = produtoService.consultarPorNome("Produto D");

        assertNull(produtoDeletado);
    }
}

