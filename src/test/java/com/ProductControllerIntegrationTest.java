package com;

import com.model.Product;
import com.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    public void shouldReturnProductList() throws Exception {
        ResponseEntity response = restTemplate.getForEntity("/products", List.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertTrue(response.hasBody());
//        assertThat(response.getBody()).isEqualTo("{\"id\":1,\"name\":\"smartphone\",\"price\":100.0}");
    }

    @Test
    public void shouldReturnProductByGivenId() throws Exception {
        ResponseEntity<Product> responseEntity =  restTemplate.getForEntity("/products/1",Product.class,1);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertTrue(responseEntity.hasBody());
    }

    @Test
    public void shouldSaveProduct() throws Exception {
      //ResponseEntity<Product> responseEntity =  restTemplate.postForEntity("/products/{id}",Product.class,1);

    }
}
