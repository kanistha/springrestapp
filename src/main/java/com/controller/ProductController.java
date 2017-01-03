package com.controller;


import com.model.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public ResponseEntity getProducts(){
        return new ResponseEntity(productService.getProducts(),HttpStatus.OK);
    }

    @RequestMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") long id){
        return new ResponseEntity<>(productService.getProduct(id),HttpStatus.OK);
    }
}

