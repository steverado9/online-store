package com.stephen.online_store.repository;

import com.stephen.online_store.entity.Cart;
import com.stephen.online_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Transactional
    @Modifying
    @Query(value = """ 
            INSERT INTO carts (user_id)
            VALUE (:userId)
            """, nativeQuery = true)
    Cart saveCart(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM carts WHERE user_id = :userId", nativeQuery = true)
    Optional<Cart> findByUserId(@Param("userId")Long userId);

}
