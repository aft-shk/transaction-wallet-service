package com.wallet.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.transaction.dto.response.BalanceResponse;
import com.wallet.transaction.services.WalletService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
@CrossOrigin("*")
public class WalletController {
	
	
	@Autowired
	
	private  WalletService walletService;

    @GetMapping("/balance")
    public BalanceResponse getBalance(
            @RequestHeader("client-id") String clientId) {

        return new BalanceResponse(
                walletService.getBalance(clientId)
        );
    }

}
