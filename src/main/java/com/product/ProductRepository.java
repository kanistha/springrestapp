package com.product;

import java.util.Arrays;
import java.util.List;

public class ProductRepository {
    public List<Product> getProducts() {
        return Arrays.asList(getProduct());
    }

    public Product getProduct(long id) {
        return getProduct();
    }

    private Product getProduct() {
        Product product = new Product();
        product.setId(new Long(1));
        product.setName("smartphone");
        product.setPrice(100);
        return product;
    }
}
