package com.wallet.transaction.dto.request;

import java.math.BigDecimal;


public class AmountRequest {
	
	private String clientId;
	private BigDecimal amount;
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
    public BigDecimal getAmount() {
		return amount;
	}
	
	public String getClientId() {
		return clientId;
	}
	
    
    
    

}
