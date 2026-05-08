package com.stephen.online_store.repository;

import com.stephen.online_store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    @Modifying
    @Query(value = """
            INSERT INTO products (title, price, description, category, image, rating)
            VALUES (:title, :price, :description, :category, :image, :rating)
            """, nativeQuery = true)
    void saveProduct(
            @Param("title") String title,
            @Param("price") Double price,
            @Param("description") String description,
            @Param("category") String category,
            @Param("image") String image,
            @Param("rating") Double rating);

    @Query(value = "SELECT * FROM products p WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "ORDER BY p.rating DESC",
            nativeQuery = true)
    List<Product> searchProducts(@Param("keyword") String keyword);


}
