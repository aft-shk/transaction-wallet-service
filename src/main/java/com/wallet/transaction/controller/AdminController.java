package com.wallet.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.transaction.dto.request.AmountRequest;
import com.wallet.transaction.services.WalletService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/wallet")
@RequiredArgsConstructor
@CrossOrigin("*")
// Manages wallet and orders 
public class AdminController {
	
	@Autowired
	private  WalletService walletservice;
	
	
	
	@PostMapping("/credit")
	public String creditWallet(@RequestBody AmountRequest request,
	                           @RequestHeader("client-id") String adminId) {

		walletservice.creditWallet(adminId, request.getClientId(), request.getAmount());
	    return "Wallet credited successfully";
	}

	
	
	@PostMapping("/debit")
	public String debitWallet(@RequestBody AmountRequest request,
	                           @RequestHeader("client-id") String adminId) {

		walletservice.debitWallet( adminId,request.getClientId(),request.getAmount());

	    return "Wallet debited successfully";
	}

	

}
