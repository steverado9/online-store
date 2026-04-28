package com.stephen.online_store.repository;

import com.stephen.online_store.entity.Cart;
import com.stephen.online_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    
    @Query(value = "SELECT * FROM carts WHERE user_id = :userId", nativeQuery = true)
    Optional<Cart> findByUserId(@Param("userId")Long userId);

}
