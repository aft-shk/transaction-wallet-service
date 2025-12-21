package com.wallet.transaction.services;

import java.math.BigDecimal;

import com.wallet.transaction.dto.response.OrderCreationResponse;


public interface OrderService {
	
	OrderCreationResponse createOrder(String clientId, BigDecimal amount);

	OrderCreationResponse getOrderDetails(String clientId, String orderId);

}
