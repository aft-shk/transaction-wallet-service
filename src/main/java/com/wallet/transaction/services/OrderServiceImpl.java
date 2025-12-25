package com.wallet.transaction.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wallet.transaction.dto.response.OrderCreationResponse;
import com.wallet.transaction.entities.Order;
import com.wallet.transaction.entities.Role;
import com.wallet.transaction.entities.User;
import com.wallet.transaction.entities.Wallet;
import com.wallet.transaction.repository.OrderRepository;
import com.wallet.transaction.repository.UserRepository;
import com.wallet.transaction.repository.WalletRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	
	@Autowired
	private  UserRepository userRepository;
	@Autowired
    private  WalletRepository walletRepository;
	@Autowired
    private  OrderRepository orderRepository;
	
	@Autowired
	private RestTemplate restTemplate = new RestTemplate();

	
	
	public OrderServiceImpl(UserRepository userRepository,
            WalletRepository walletRepository,
            OrderRepository orderRepository) {
				this.userRepository = userRepository;
				this.walletRepository = walletRepository;
				this.orderRepository = orderRepository;	
				}

	
	
	@Override
	public OrderCreationResponse createOrder(String clientId, BigDecimal amount) {
		
		
		User user = userRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("User not found"));

		
		if (user.getRole() != Role.CLIENT) {
            throw new RuntimeException("Only clients can create orders");
        }
		
		// Fetch wallet with lock
        Wallet wallet = walletRepository.findByUserForUpdate(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (wallet.getWallet_balance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient wallet balance");
        }
        
        wallet.setWallet_balance(wallet.getWallet_balance().subtract(amount));
        walletRepository.save(wallet);
        
        
        String fulfillmentId = restTemplate.postForObject(
                "https://jsonplaceholder.typicode.com/posts",
                null,
                String.class
        );
        
        Order order = new Order();
        order.setOrder_id(UUID.randomUUID().toString());
        order.setUser(user);
        order.setAmount(amount);
        order.setStatus("CREATED");
        order.setCreatedAt(LocalDateTime.now());
        order.setFulfillmentId(fulfillmentId);
      
        
        orderRepository.save(order);

        


        return new OrderCreationResponse(
                order.getOrder_id(),
                order.getAmount(),
                order.getStatus(),
                order.getFulfillmentId()
        );

		
	}

	@Override
    public OrderCreationResponse getOrderDetails(String clientId, String orderId) {

        User user = userRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Unauthorized access");
        }

        return new OrderCreationResponse(
                order.getOrder_id(),
                order.getAmount(),
                order.getStatus(),
                order.getFulfillmentId()
        );
    }

}
