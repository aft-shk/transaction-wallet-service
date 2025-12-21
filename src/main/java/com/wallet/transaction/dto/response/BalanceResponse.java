package com.wallet.transaction.dto.response;

import java.math.BigDecimal;




public class BalanceResponse {
	
	public BigDecimal getWallet_balance() {
		return wallet_balance;
	}

	public void setWallet_balance(BigDecimal wallet_balance) {
		this.wallet_balance = wallet_balance;
	}

	private BigDecimal wallet_balance;
	
	public BalanceResponse(BigDecimal balance) {
		this.wallet_balance = balance;
	}
	
	
	

}





