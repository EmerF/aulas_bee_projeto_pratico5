package com.ambev.projetopratico5.it;

import com.ambev.projetopratico5.dto.ProductDTO;
import com.ambev.projetopratico5.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = MongoDBSettings.class)
public class ProductControllerIT {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private ProductDTO productDTO;
    private ProductDTO productDTOReturns;
    private String productJson;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        productRepository.deleteAll();
        createProductForTest();
    }

    @Test
    public void createProductAndValidationIT() throws Exception {
        createProductBaseIT();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products/find/{name}"
                                ,productDTO.getName()))
                .andExpect(status().isOk())
                .andReturn();
        String resultProduct = result.getResponse().getContentAsString();
        assert resultProduct.contains(productDTO.getName());

    }

    @Test
    public void registerProductNullIT() throws Exception {
        createProductDTONameNull();
        productJson = objectMapper.writeValueAsString(productDTO);
        createProductNaBaseNullError();

    }

    @Test
    public void searchProductByNameNotFoundIT() throws Exception {
        createProduct();
        productDTO.setName("Different name!");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/products/find/{name}",
                                productDTO.getName()))
                .andExpect(status().isNotFound())
                .andReturn();
        String ret = result.getResponse().getContentAsString();
        assert ret.isEmpty();

    }

    @Test
    public void deleteOneProductIT() throws Exception {
        createProduct();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .delete("/store/products/{id}", productDTOReturns.getId()))
                .andExpect(status().isOk())
                .andReturn();
        String ret = result.getResponse().getContentAsString();
        assert ret.contains("Successfully deleted");

    }


    private void createProduct() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/store/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andReturn();
        productDTOReturns = objectMapper.readValue(result.getResponse().getContentAsString(), ProductDTO.class);

    }

    private void createProductNaBaseNullError() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/store/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isBadRequest())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();

        assertThat(responseBody, containsString("the price must be greater than 1"));
        assertThat(responseBody, containsString("Name cannot be empty"));

    }

    private void createProductForTest() throws JsonProcessingException {
        createProductDTO();
        productJson = objectMapper.writeValueAsString(productDTO);
    }

    private void createProductDTONameNull() {
        productDTO = new ProductDTO();
        productDTO.setDescriptions("ProductIT description");
        productDTO.setPrice(0);
    }

    private void createProductDTO() {
        productDTO = new ProductDTO();
        productDTO.setName("Product Test IT");
        productDTO.setPrice(10.00);
        productDTO.setDescriptions("in the palm of your hand");
    }
    private void createProductBaseIT() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/store/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isOk())
                .andReturn();
        productDTOReturns = objectMapper.readValue(result.getResponse().getContentAsString(),ProductDTO.class);
    }
}
