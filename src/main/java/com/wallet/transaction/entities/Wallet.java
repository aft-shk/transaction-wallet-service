package com.wallet.transaction.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;


@Entity
@Table(name = "wallets")
@Builder
public class Wallet {

	public Wallet() {
		super();
		
	}

	public Long getWallet_id() {
		return wallet_id;
	}

	public void setWallet_id(Long wallet_id) {
		this.wallet_id = wallet_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getWallet_balance() {
		return wallet_balance;
	}

	public void setWallet_balance(BigDecimal wallet_balance) {
		this.wallet_balance = wallet_balance;
	}

	public Wallet(Long wallet_id, User user, BigDecimal wallet_balance) {
		super();
		this.wallet_id = wallet_id;
		this.user = user;
		this.wallet_balance = wallet_balance;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long wallet_id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, unique = true)
	private User user;
	
    @Column(nullable = false, precision = 15, scale = 2)
	private BigDecimal wallet_balance;
	
}
