package com.surjo.admin.service;

import com.surjo.admin.model.Product;

import java.util.List;

/**
 * Created by sanjoy on 3/9/19.
 */
public interface ProductService {

    Long createProduct(Product product);

    List<Product> getAllProduct();

    void deleteProduct(Long id);

    Product getProductById(Long id);

    List<Product> searchProduct(String name);
}
