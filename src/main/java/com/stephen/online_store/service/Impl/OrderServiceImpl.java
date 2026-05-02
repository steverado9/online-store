package com.stephen.online_store.service.Impl;

import com.stephen.online_store.entity.Order;
import com.stephen.online_store.repository.OrderRepository;
import com.stephen.online_store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
