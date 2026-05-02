package com.stephen.online_store.entity;

import com.stephen.online_store.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private String rrr;

    @Column(nullable = false)
    private String payerName;

    @Column(nullable = false)
    private String payerEmail;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Order() {
    }

    public Order(String orderNumber, Double amount, Status status, String rrr, String payerName, String payerEmail, LocalDateTime createdAt) {
        this.orderNumber = orderNumber;
        this.amount = amount;
        this.status = status;
        this.rrr = rrr;
        this.payerName = payerName;
        this.payerEmail = payerEmail;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRrr() {
        return rrr;
    }

    public void setRrr(String rrr) {
        this.rrr = rrr;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
