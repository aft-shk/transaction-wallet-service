package com.wallet.transaction.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;


@Entity
@Table(name = "orders")
@Builder

public class Order {
	
	
	public Order(Long id, String order_id, User user, BigDecimal amount, String status, LocalDateTime createdAt, String fulfillmentId) {
		super();
		this.id = id;
		this.orderId = order_id;
		this.user = user;
		this.amount = amount;
		this.status = status;
		this.createdAt = createdAt;
		this.fulfillmentId = fulfillmentId;
	}



	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getOrder_id() {
		return orderId;
	}



	public void setOrder_id(String order_id) {
		this.orderId= order_id;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public BigDecimal getAmount() {
		return amount;
	}



	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getFulfillmentId() {
		return fulfillmentId;
	}



	public void setFulfillmentId(String fulfillmentId) {
		this.fulfillmentId = fulfillmentId;
	}




	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "order_id",nullable = false, unique = true)
	private String orderId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;  // this will be ids for clients only as admins are not authorized for ordering.
	
	@Column(nullable = false, precision = 15, scale = 2)
	private BigDecimal amount;
	
	@Column(nullable = false)
	private String status;
	
	@Column(name ="fullfillment_id")
    private String fulfillmentId;

	
	


	@Column(nullable = false)
    private LocalDateTime createdAt;

	
	

}
