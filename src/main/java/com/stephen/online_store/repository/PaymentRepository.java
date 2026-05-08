package com.stephen.online_store.repository;

import com.stephen.online_store.entity.Payment;
import com.stephen.online_store.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Transactional
    @Modifying
    @Query(value = """
            INSERT INTO payments (tx_ref, email, amount, status, transactionId)
            VALUE(:txRef, :email, :amount, :status, :transactionId)
            """, nativeQuery = true)
    void savePayment(
            @Param("txRef") String txRef,
            @Param("email") String email,
            @Param(("amount")) Double amount,
            @Param("status") Status status,
            @Param("transactionId") String transactionId);

    @Query(value = "SELECT * FROM  payments WHERE tx_ref = :txRef", nativeQuery = true)
    Optional<Payment> findByTxref(@Param("txRef") String txRef);
}
