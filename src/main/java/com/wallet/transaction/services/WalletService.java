package com.wallet.transaction.services;

import java.math.BigDecimal;

public interface WalletService {
	
	 public void creditWallet(String adminClientId, String clientId, BigDecimal amount);

	 public void debitWallet(String adminClientId, String clientId, BigDecimal amount);

	 BigDecimal getBalance(String clientId);

}
