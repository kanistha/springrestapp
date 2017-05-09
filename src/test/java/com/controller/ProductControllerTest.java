package com.controller;

import com.model.Product;
import com.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;


    @Autowired
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Test
    public void testGetAllProducts() throws Exception {
        //Given
        List<Product> products =  new ArrayList<Product>();
        products.add(new Product(new Long(1),"Product1", 100));
        given(productService.getProducts()).willReturn(products);

        //When & Then
        this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].name", is(products.get(0).getName())))
                .andExpect(jsonPath("$[0].price", is(products.get(0).getPrice())));

        //Then
        verify(productService, atLeastOnce()).getProducts();
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product(10L, "Product1", 100);
        given(productService.getProduct(anyLong())).willReturn(product);

        mockMvc.perform(get("/products/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(10)))
                .andExpect(jsonPath("$.name",is(product.getName())))
                .andExpect(jsonPath("$.price", is(product.getPrice())));

        verify(productService).getProduct(anyLong());
    }

    @Test
    public void testCreateProduct() throws Exception {
        Long id = new Long(10);
        Product product = new Product(id, "Product1", 100);
        given(productService.saveProduct(any(Product.class))).willReturn(product);

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(product.getId().intValue())))
                .andExpect(jsonPath("$.name",is(product.getName())))
                .andExpect(jsonPath("$.price", is(product.getPrice())));

        verify(productService).saveProduct(any(Product.class));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Long id = new Long(10);
        Product productToUpdate = new Product(id, "product", 200);
        given(productService.updateProduct(any(Product.class))).willReturn(productToUpdate);

        mockMvc.perform(put("/products/$id")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(json(productToUpdate)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id",is(productToUpdate.getId().intValue())))
            .andExpect(jsonPath("$.name", is(productToUpdate.getName())))
            .andExpect(jsonPath("$.price",is(productToUpdate.getPrice())));
    }

    @Test
    public void testDeleteProduct() throws Exception {
            Long id = new Long(10);

            mockMvc.perform(delete("/products/$id"))
                    .andExpect(status().isOk());

    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
