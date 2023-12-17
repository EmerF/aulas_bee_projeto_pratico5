package com.ambev.projetopratico5;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ambev.projetopratico5.dto.ProdutoDTO;
import com.ambev.projetopratico5.repository.ProdutoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest(classes = MongoConfig.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private String jsonProduto;
    private ProdutoDTO produtoDTO;
    private ProdutoDTO produtoDTOResponse;

    @BeforeEach
    public void configurar() throws JsonProcessingException {
        produtoRepository.deleteAll();
        inicializarProdutoDeTeste();
    }

    @Test
    public void deveCriarERecuperarProduto() throws Exception {
        adicionarProdutoNoBanco();
        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/produtos/consultar/{nome}", produtoDTO.getNome()))
                .andExpect(status().isOk())
                .andReturn();

        String produtoRecuperado = resultado.getResponse().getContentAsString();
        assertThat(produtoRecuperado, containsString(produtoDTO.getNome()));
    }

    @Test
    public void deveDeletarProduto() throws Exception {
        adicionarProdutoNoBanco();
        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/produtos/{id}", produtoDTOResponse.getId()))
                .andExpect(status().isOk())
                .andReturn();

        String mensagemResposta = resultado.getResponse().getContentAsString();
        assertThat(mensagemResposta, containsString("Produto deletado"));
    }

    @Test
    public void naoDeveEncontrarProdutoInexistentePorNome() throws Exception {
        adicionarProdutoNoBanco();
        produtoDTO.setNome("Nome diferente!321ed");
        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/produtos/consultar/{nome}", produtoDTO.getNome()))
                .andExpect(status().isNotFound())
                .andReturn();

        String mensagemResposta = resultado.getResponse().getContentAsString();
        assertThat(mensagemResposta, containsString("Produto não encontrado"));
    }

    @Test
    public void deveRejeitarCriacaoDeProdutoInvalido() throws Exception {
        prepararProdutoDTOInvalido();
        jsonProduto = objectMapper.writeValueAsString(produtoDTO);
        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProduto))
                .andExpect(status().isBadRequest())
                .andReturn();
        String response = resultado.getResponse().getContentAsString();
        assertThat(response, containsString("nome nao pode ser vazio"));
        assertThat(response, containsString("tamanho minimo de 10 caracteres"));
        assertThat(response, containsString("valor minimo de 1"));
    }
    @Test
    public void deveAtualizarProdutoComSucesso() throws Exception {
        adicionarProdutoNoBanco();

        produtoDTO.setDescricao("Descricao atualizada");
        produtoDTO.setPreco(3.99);
        String jsonProdutoAtualizado = objectMapper.writeValueAsString(produtoDTO);

        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/produtos/{id}", produtoDTOResponse.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProdutoAtualizado))
                .andExpect(status().isOk())
                .andReturn();

        String corpoResposta = resultado.getResponse().getContentAsString();
        assertThat(corpoResposta, containsString("Descricao atualizada"));
        assertThat(corpoResposta, containsString("3.99"));
    }

    @Test
    public void deveConsultarProdutoPorNomeComSucesso() throws Exception {
        adicionarProdutoNoBanco();

        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/produtos/consultar/{nome}", produtoDTO.getNome()))
                .andExpect(status().isOk())
                .andReturn();

        String corpoResposta = resultado.getResponse().getContentAsString();
        assertThat(corpoResposta, containsString(produtoDTO.getNome()));
    }
    private void prepararProdutoDTOInvalido() {
        produtoDTO = new ProdutoDTO();
        produtoDTO.setDescricao("P");
        produtoDTO.setPreco(0.5);
    }

    private void adicionarProdutoNoBanco() throws Exception {
        jsonProduto = objectMapper.writeValueAsString(produtoDTO);
        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProduto))
                .andExpect(status().isOk())
                .andReturn();
        produtoDTOResponse = objectMapper.readValue(resultado.getResponse().getContentAsString(), ProdutoDTO.class);
    }

    private void inicializarProdutoDeTeste() throws JsonProcessingException {
        produtoDTO = new ProdutoDTO();
        produtoDTO.setNome("Produto Teste");
        produtoDTO.setDescricao("Descrição do Produto Teste");
        produtoDTO.setPreco(2.95);
        jsonProduto = objectMapper.writeValueAsString(produtoDTO);
    }
}
