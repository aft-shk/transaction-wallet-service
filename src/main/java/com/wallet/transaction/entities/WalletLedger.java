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
@Table(name = "wallet_ledger")
@Builder
public class WalletLedger {
	
	public Long getLedger_id() {
		return ledger_id;
	}


	public void setLedger_id(Long ledger_id) {
		this.ledger_id = ledger_id;
	}


	public Wallet getWallet() {
		return wallet;
	}


	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}


	public User getEntryperformedBy() {
		return entryperformedBy;
	}


	public void setEntryperformedBy(User entryperformedBy) {
		this.entryperformedBy = entryperformedBy;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public LocalDateTime getCreation() {
		return creation;
	}


	public void setCreation(LocalDateTime creation) {
		this.creation = creation;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ledger_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wallet_id", nullable = false)
	private Wallet wallet;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "user_id", nullable = false)
	private User entryperformedBy;
	
	@Column(nullable = false, precision = 15, scale = 2)
	private BigDecimal amount;
	
	
	@Column(nullable = false)
	private LocalDateTime creation;
	
	
	

}
