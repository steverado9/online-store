package com.stephen.online_store.repository;

import com.stephen.online_store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders WHERE rrr = :rrr", nativeQuery = true)
    Optional<Order> findByRrr(@Param("rrr") String rrr);
}
