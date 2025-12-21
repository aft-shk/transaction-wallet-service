package com.wallet.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.transaction.dto.request.OrderRequest;
import com.wallet.transaction.dto.response.OrderCreationResponse;
import com.wallet.transaction.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin("*")

public class OrderController {
	
	@Autowired
	private  OrderService orderService;

	@PostMapping
	public OrderCreationResponse createOrder(
	       @RequestHeader("client-id") String clientId,
	        @RequestBody OrderRequest request) {

		return orderService.createOrder(clientId, request.getAmount());
	}

    @GetMapping("/{orderId}")
    public OrderCreationResponse getOrder(
            @RequestHeader("client-id") String clientId,
            @PathVariable String orderId) {

        return orderService.getOrderDetails(clientId, orderId);
    }

}
