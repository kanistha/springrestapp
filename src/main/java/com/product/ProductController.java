package com.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Product> getProduct(@RequestParam(value = "id", defaultValue = "0") long id){
        return new ResponseEntity<>(productService.getProduct(id),HttpStatus.OK);
    }
}

