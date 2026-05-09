package com.stephen.online_store.repository;

import com.stephen.online_store.entity.Cart;
import com.stephen.online_store.entity.CartItem;
import com.stephen.online_store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Transactional
    @Modifying
    @Query(value = """
            INSERT INTO cart_items (cart_id, product_id, quantity)
            VALUE (:cartId, :productId, :quantity)
            """, nativeQuery = true)
    void saveCartItem(
            @Param("quantity") int quantity,
            @Param("cartId") Long cartId,
            @Param("productId") Long productId);

    @Query(value = "SELECT * FROM cart_items WHERE cart_id = :cartId AND product_id = :productId", nativeQuery = true)
    Optional<CartItem> findByCartAndProduct(@Param("cartId") Long cartId, @Param("productId") Long productId);

    @Transactional
    @Modifying
    @Query(value = """
            UPDATE cart_items
            SET quantity = :quantity
            WHERE id = :cartItemId
            """, nativeQuery = true)
    void saveCartItemQuantity(
            @Param("quantity") int quantity,
            @Param("cartItemId") Long cartItemId);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cart_items WHERE cart_id = :cartId", nativeQuery = true)
    void deleteCartItemsByCartId(@Param("cartId") Long cartId);

}
