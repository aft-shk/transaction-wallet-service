package com.wallet.transaction.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.transaction.entities.Role;
import com.wallet.transaction.entities.User;
import com.wallet.transaction.entities.Wallet;
import com.wallet.transaction.entities.WalletLedger;
import com.wallet.transaction.repository.UserRepository;
import com.wallet.transaction.repository.WalletLedgerRepository;
import com.wallet.transaction.repository.WalletRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
	
	
	@Autowired
	private  UserRepository userRepository;
	@Autowired
    private  WalletRepository walletRepository;
	@Autowired
    private  WalletLedgerRepository walletLedgerRepository;

	@Override
	@Transactional
	public void creditWallet(String adminClientId, String clientId, BigDecimal amount) {

		
		User adminUser = userRepository.findByClientId(adminClientId)
				.orElseThrow(() -> new RuntimeException("Admin not found"));
		
		if (adminUser.getRole() != Role.ADMIN) {
	        throw new RuntimeException("Only admin can credit wallet");
	    }
		
		
		User clientUser = userRepository.findByClientId(clientId)
	            .orElseThrow(() -> new RuntimeException("Client not found"));

	    Wallet wallet = walletRepository.findByUserForUpdate(clientUser)
	            .orElseThrow(() -> new RuntimeException("Wallet not found"));

	    wallet.setWallet_balance(wallet.getWallet_balance().add(amount));
	    walletRepository.save(wallet);

	    WalletLedger ledger = new WalletLedger();
	    ledger.setWallet(wallet);
	    ledger.setEntryperformedBy(adminUser);
	    ledger.setAmount(amount);
	    ledger.setCreation(LocalDateTime.now());

	    walletLedgerRepository.save(ledger);
	
 
        
	}

        

    
    @Override
    @Transactional
    public void debitWallet(String adminClientId, String clientId, BigDecimal amount) {

       
        User admin = userRepository.findByClientId(adminClientId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (admin.getRole() != Role.ADMIN) {
            throw new RuntimeException("Only admin can debit wallet");
        }

        
        User client = userRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Fetch wallet with lock
        Wallet wallet = walletRepository.findByUserForUpdate(client)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        // Credit wallet
        if (wallet.getWallet_balance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient wallet balance");
        }

        
        wallet.setWallet_balance(wallet.getWallet_balance().subtract(amount));
        walletRepository.save(wallet);


        WalletLedger ledger = new WalletLedger();
        ledger.setWallet(wallet);
        ledger.setEntryperformedBy(admin);
        ledger.setAmount(amount);
        ledger.setCreation(LocalDateTime.now());

        walletLedgerRepository.save(ledger);
        
    }


    @Override
    public BigDecimal getBalance(String clientId) {

        User user = userRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        return wallet.getWallet_balance();
    }


}
