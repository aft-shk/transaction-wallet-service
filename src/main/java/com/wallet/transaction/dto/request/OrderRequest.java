package com.wallet.transaction.dto.request;

import java.math.BigDecimal;



public class OrderRequest {
	
    private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


}
