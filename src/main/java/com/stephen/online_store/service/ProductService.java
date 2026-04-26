package com.stephen.online_store.service;

import com.stephen.online_store.entity.Product;

import java.util.List;

public interface ProductService {
    void saveProduct(Product product);

    List<Product> getAllProducts();

    List<Product> searchProducts(String keyword);
}
