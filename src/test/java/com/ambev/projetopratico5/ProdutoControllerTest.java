package com.ambev.projetopratico5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ambev.projetopratico5.model.Produto;
import com.ambev.projetopratico5.service.ProdutoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProdutoService produtoService;

    @LocalServerPort
    private int port;

    @Test
    public void cadastrarProduto() {
        Produto produto = new Produto(1, "Produto 1", "Descrição do Produto 1", 10.0);
        ResponseEntity<Void> response = restTemplate.postForEntity(getUrl("/produtos"), produto, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, produtoService.getProdutos().size());
    }

    @Test
    public void atualizarProduto() {
        Produto produto = new Produto(1, "Produto 1", "Descrição do Produto 1", 10.0);
        produtoService.cadastrarProduto(produto);

        Produto produtoAtualizado = new Produto(1, "Produto 1 Atualizado", "Descrição do Produto 1 Atualizado", 15.0);
        restTemplate.put(getUrl("/produtos/Produto 1"), produtoAtualizado);

        Produto produtoConsultado = produtoService.consultarPorNome("Produto 1 Atualizado");
        assertEquals("Produto 1 Atualizado", produtoConsultado.getNome());
    }

    @Test
    public void consultarPorNome() {
        Produto produto = new Produto(1, "Produto 1", "Descrição do Produto 1", 10.0);
        produtoService.cadastrarProduto(produto);

        ResponseEntity<Produto> response = restTemplate.getForEntity(getUrl("/produtos/Produto 1"), Produto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto 1", response.getBody().getNome());
    }

    @Test
    public void deletarPorNome() {
        Produto produto = new Produto(1, "Produto 1", "Descrição do Produto 1", 10.0);
        produtoService.cadastrarProduto(produto);

        restTemplate.delete(getUrl("/produtos/Produto 1"));

        assertNull(produtoService.consultarPorNome("Produto 1"));
    }

    private String getUrl(String uri) {
        return "http://localhost:" + port + uri;
    }
}














