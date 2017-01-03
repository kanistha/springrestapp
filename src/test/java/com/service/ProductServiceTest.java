package com.service;

import com.model.Product;
import com.repository.ProductRepository;
import com.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Test
    public void shouldReturnProducts() throws Exception {
        Product product = new Product();
        when(productRepository.getProducts()).thenReturn(Arrays.asList(product));
        assertNotNull(productService.getProducts());
        assertEquals(1, productService.getProducts().size());
    }
}
