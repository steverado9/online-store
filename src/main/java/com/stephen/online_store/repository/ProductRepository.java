package com.stephen.online_store.repository;

import com.stephen.online_store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM products WHERE category = :category", nativeQuery = true)
    List<Product> findByCategory(String category);
}
