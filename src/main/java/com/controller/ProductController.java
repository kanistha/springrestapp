package com.controller;

import com.model.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity getProducts(){
        return new ResponseEntity(productService.getProducts(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id){
        return new ResponseEntity<>(productService.getProduct(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product finalProduct = productService.saveProduct(product);
        return new ResponseEntity<Product>(finalProduct, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product productToUpdate){
        return new ResponseEntity<Product>(productToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

}

