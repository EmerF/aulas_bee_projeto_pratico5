package com.ambev.projetopratico5.it;

import com.ambev.projetopratico5.model.Produto;
import com.ambev.projetopratico5.service.ProdutoService;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
    @Transactional
    public class ProdutoServiceTest {
        @Autowired
        private ProdutoService produtoService;

        @Test
        public void testCadastrarProduto() {
            Produto produto = new Produto();
            produto.setNome("Produto de Teste");
            produto.setPreco(10.0);

            Produto produtoSalvo = produtoService.cadastrarProduto(produto);

            assertNotNull(produtoSalvo.getId());
            assertEquals(produto.getNome(), produtoSalvo.getNome());
            assertEquals(produto.getPreco(), produtoSalvo.getPreco());
        }

        @Test
        public void testAtualizarProduto() {
            Produto produto = new Produto();
            produto.setNome("Produto de Teste");
            produto.setPreco(10.0);

            Produto produtoSalvo = produtoService.cadastrarProduto(produto);

            assertNotNull(produtoSalvo.getId());
            assertEquals(produto.getNome(), produtoSalvo.getNome());
            assertEquals(produto.getPreco(), produtoSalvo.getPreco());

            produtoSalvo.setNome("Produto de Teste Atualizado");
            produtoSalvo.setPreco(20.0);

            Produto produtoAtualizado = produtoService.atualizarProduto(produtoSalvo.getId(), produtoSalvo);

            assertEquals(produtoSalvo.getId(), produtoAtualizado.getId());
            assertEquals(produtoSalvo.getNome(), produtoAtualizado.getNome());
            assertEquals(produtoSalvo.getPreco(), produtoAtualizado.getPreco());
        }

        @Test
        public void testConsultarProdutoPorNome() {
            Produto produto = new Produto();
            produto.setNome("Produto de Teste");
            produto.setPreco(10.0);

            Produto produtoSalvo = produtoService.cadastrarProduto(produto);

            assertNotNull(produtoSalvo.getId());
            assertEquals(produto.getNome(), produtoSalvo.getNome());
            assertEquals(produto.getPreco(), produtoSalvo.getPreco());

            Produto produtoConsultado = produtoService.consultarProdutoPorNome(produtoSalvo.getNome());

            assertEquals(produtoSalvo.getId(), produtoConsultado.getId());
            assertEquals(produtoSalvo.getNome(), produtoConsultado.getNome());
            assertEquals(produtoSalvo.getPreco(), produtoConsultado.getPreco());
        }

        @Test
        public void testDeletarProdutoPorNome() {
            Produto produto = new Produto();
            produto.setNome("Produto de Teste");
            produto.setPreco(10.0);

            Produto produtoSalvo = produtoService.cadastrarProduto(produto);

            assertNotNull(produtoSalvo.getId());
            assertEquals(produto.getNome(), produtoSalvo.getNome());
            assertEquals(produto.getPreco(), produtoSalvo.getPreco());

            produtoService.deletarProdutoPorNome(produtoSalvo.getNome());

            Produto produtoConsultado = produtoService.consultarProdutoPorNome(produtoSalvo.getNome());

            assertEquals(null, produtoConsultado);
        }
    }



