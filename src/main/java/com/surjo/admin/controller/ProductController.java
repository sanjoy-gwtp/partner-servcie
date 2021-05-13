package com.surjo.admin.controller;

import com.surjo.admin.model.Product;
import com.surjo.admin.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by sanjoy on 3/9/19.
 */
@RestController
@RequestMapping(path = "/product",produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @GetMapping
    public ResponseEntity getAllProduct() {
        List<Product> productList = productService.getAllProduct();
        return ResponseEntity.ok(productList);
    }

    @GetMapping(path = "/search/{name}")
    public ResponseEntity searchProduct(@PathVariable("name") String  name) {
        List<Product> productList = productService.searchProduct(name);
        return ResponseEntity.ok(productList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @DeleteMapping
    public ResponseEntity deleteProduct(@RequestParam(name="id",required = true)Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.accepted().build();
    }
}
