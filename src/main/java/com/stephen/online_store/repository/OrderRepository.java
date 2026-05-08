package com.stephen.online_store.repository;

import com.stephen.online_store.entity.Order;
import com.stephen.online_store.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


public interface OrderRepository extends JpaRepository<Order, Long> {

    @Transactional
    @Modifying
    @Query(value = """
            INSERT INTO orders (user_email, total_amount, status, tx_ref, transaction_id, created_at)
            VALUE (:email, :totalAmount, :status, :txRef, :transactionId, :createdAt)
            """, nativeQuery = true)
    void saveOrder(
            @Param("email") String email,
            @Param("totalAmount") Double totalAmount,
            @Param("status") Status status,
            @Param("txRef") String txRef,
            @Param("transactionId") String transactionId,
            @Param("createdAt") LocalDateTime createdAt);
}
