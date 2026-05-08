package com.stephen.online_store.service;

import com.stephen.online_store.entity.Payment;
import com.stephen.online_store.enums.Status;

import java.util.Optional;

public interface PaymentService {
    void savePayment(String txRef, String email, Double amount, Status status, String transactionId);

    Optional<Payment> findByTxref(String txRef);
}
