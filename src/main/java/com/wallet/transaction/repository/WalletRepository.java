package com.wallet.transaction.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.wallet.transaction.entities.User;
import com.wallet.transaction.entities.Wallet;

import jakarta.persistence.LockModeType;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
	
	
	  Optional<Wallet> findByUser(User user);
	  
	  @Lock(LockModeType.PESSIMISTIC_WRITE)
	  @Query("select w from Wallet w where w.user = :user")
	  Optional<Wallet> findByUserForUpdate(User user);

}
