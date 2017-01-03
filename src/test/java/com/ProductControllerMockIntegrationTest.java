package com;

import com.Application;
import com.model.Product;
import com.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerMockIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProductService productService;

    @Test
    public void shouldReturnProductList() throws Exception {
        List<Product> productList = Arrays.asList(new Product());
        given(productService.getProducts()).willReturn(productList);
        ResponseEntity response = restTemplate.getForEntity("/products", List.class);
        verify(productService, only()).getProducts();
    }

    @Test
    public void shouldReturnProductByGivenId() throws Exception {
        given(productService.getProduct(anyLong())).willReturn(new Product());
        ResponseEntity<Product> responseEntity =  restTemplate.getForEntity("/products/1",Product.class,1);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertTrue(responseEntity.hasBody());
        verify(productService,atLeastOnce()).getProduct(1);
    }

    @Test
    public void shouldSaveProduct() throws Exception {
      //ResponseEntity<Product> responseEntity =  restTemplate.postForEntity("/products/{id}",Product.class,1);

    }
}
