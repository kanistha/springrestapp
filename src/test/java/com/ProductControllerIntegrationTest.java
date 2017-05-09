package com;

import com.model.Product;
import com.repositories.ProductRepository;
import org.junit.Before;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        productRepository.deleteAll();
    }

    @Test
    public void shouldReturnProductList() throws Exception {
        ResponseEntity response = restTemplate.getForEntity("/products", List.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
//        assertThat(response.getBody()).isEqualTo("{\"id\":1,\"name\":\"smartphone\",\"price\":100.0}");
    }

    @Test
    public void shouldSaveProduct() throws Exception {
        Product product = new Product(1L, "smartphone", 200.00);
        ResponseEntity<Product> responseEntity = restTemplate.postForEntity("/products", product, Product.class, Product.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(product);
    }

    @Test
    public void shouldReturnProductByGivenId() throws Exception {
        //Given
        Product product = new Product(1L, "smartphone", 200.00);
        restTemplate.postForEntity("/products", product, Product.class, Product.class);

        //When
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity("/products/1", Product.class, 1);

        //Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody().getId()).isEqualTo(1);
//        assertThat(responseEntity.getBody().getName()).isEqualTo(product.getName());
//        assertThat(responseEntity.getBody().getPrice()).isEqualTo(product.getPrice());
    }

}
