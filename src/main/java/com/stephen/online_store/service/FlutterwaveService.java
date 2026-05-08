package com.stephen.online_store.service;

import java.util.Map;

public interface FlutterwaveService {
    String createPayment(String email, String name, double amount, String txRef);

    Map<String, Object> verifyPayment(String transactionId);
}
