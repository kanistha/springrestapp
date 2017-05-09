package com.service;

import com.model.Product;
import com.repositories.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void getProducts_returnsAllProducts() throws Exception {
        //given
        Product product = new Product(1L, "Mobile", 1000.0);
        given(productRepository.findAll()).willReturn(asList(product));

        //when
        List<Product> products = productService.getProducts();

        //then
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(1);
        assertThat(products.get(0).getId()).isEqualTo(1);
        assertThat(products.get(0).getName()).isEqualTo("Mobile");
        assertThat(products.get(0).getPrice()).isEqualTo(1000.0);
        verify(productRepository, atLeastOnce()).findAll();
    }

    @Test
    public void getProducts_whenNoRecords_returnsNull() throws Exception {
        given(productRepository.findAll()).willReturn(null);

        assertThat(productService.getProducts()).isNullOrEmpty();

        verify(productRepository, atLeastOnce()).findAll();
    }

    @Test
    public void getProduct_returnsProductByGivenId() throws Exception {
        given(productRepository.findOne(1L))
                .willReturn(new Product(1L, "smartphone", 1000.0));

        assertThat(productService.getProduct(1L)).isNotNull();
        assertThat(productService.getProduct(1).getName()).isEqualTo("smartphone");
        assertThat(productService.getProduct(1).getPrice()).isEqualTo(1000.0);
        verify(productRepository, atLeastOnce()).findOne(1L);

    }

    @Test
    public void saveProductTest() throws Exception {
        Product product = new Product(1L, "Mobile", 1000.0);
        given(productRepository.save(product)).willReturn(product);

        Product savedProduct =  productService.saveProduct(product);

       assertThat(savedProduct).isNotNull();
       assertThat(savedProduct.getName()).isEqualTo("Mobile");
       assertThat(savedProduct.getPrice()).isEqualTo(1000.0);
    }

    @Test
    public void deleteProductTest() throws Exception {


    }
}

