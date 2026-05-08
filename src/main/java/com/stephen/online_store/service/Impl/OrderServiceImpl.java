package com.stephen.online_store.service.Impl;

import com.stephen.online_store.entity.Order;
import com.stephen.online_store.enums.Status;
import com.stephen.online_store.repository.OrderRepository;
import com.stephen.online_store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void saveOrder(String email, Double totalAmount, Status status, String txRef, String transactionId, LocalDateTime createdAt) {
        orderRepository.saveOrder(email, totalAmount, status, txRef, transactionId, createdAt);
    }
}
