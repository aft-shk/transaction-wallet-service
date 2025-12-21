package com.wallet.transaction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.transaction.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
    Optional<Order> findByOrderId(String orderId);


}
