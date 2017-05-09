package com.service;

import com.model.Product;
import com.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Scope("prototype")
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return(List<Product>)productRepository.findAll();
    }

    public Product getProduct(long id) {
        return productRepository.findOne(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.delete(Long.parseLong(id));
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
}
