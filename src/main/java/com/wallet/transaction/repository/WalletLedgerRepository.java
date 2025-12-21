package com.wallet.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.transaction.entities.WalletLedger;

public interface WalletLedgerRepository extends JpaRepository<WalletLedger, Long> {
	
	

}
