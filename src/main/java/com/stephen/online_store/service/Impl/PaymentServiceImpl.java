package com.stephen.online_store.service.Impl;

import com.stephen.online_store.entity.Payment;
import com.stephen.online_store.enums.Status;
import com.stephen.online_store.repository.PaymentRepository;
import com.stephen.online_store.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void savePayment(String txRef, String email, Double amount, Status status, String transactionId) {
        paymentRepository.savePayment(txRef, email, amount, status, transactionId);
    }

    @Override
    public Optional<Payment> findByTxref(String txRef) {
        return paymentRepository.findByTxref(txRef);
    }
}
