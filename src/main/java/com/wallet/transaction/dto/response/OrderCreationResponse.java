package com.wallet.transaction.dto.response;

import java.math.BigDecimal;

public class OrderCreationResponse {
	
	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
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


	private String orderId;
    private BigDecimal amount;
    private String status;
    
    
    public OrderCreationResponse(String orderId, BigDecimal amount, String status ) {
    	this.orderId = orderId;
    	this.amount=amount;
    	this.status = status;
    	
    }

}




