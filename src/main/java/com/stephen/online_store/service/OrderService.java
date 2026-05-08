package com.stephen.online_store.service;

import com.stephen.online_store.entity.Order;
import com.stephen.online_store.enums.Status;

import java.time.LocalDateTime;

public interface OrderService {
    void saveOrder(String email, Double totalAmount, Status status, String txRef, String transactionId, LocalDateTime createdAt);
}
