package com.shree.DigitalWallet.controllers;

import com.shree.DigitalWallet.DTO.AmountRequest;
import com.shree.DigitalWallet.entity.Wallet;
import com.shree.DigitalWallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;


    @GetMapping("/{userId}")
    public Wallet getWalletByUserId(@PathVariable Long userId) {
        return walletService.getWalletByUserId(userId);
    }

    @GetMapping("/{userId}/balance")
    public BigDecimal showBalance(@PathVariable Long userId) {
        return walletService.showBalance(userId);
    }

    @PostMapping("/{userId}/deposit")
    public BigDecimal depositMoney(@PathVariable Long userId, @RequestBody AmountRequest request) {
        return walletService.depositMoney(request.getAmount(),userId);
    }

    @PostMapping("/{userId}/withdraw")
    public BigDecimal withdrawMoney(@PathVariable Long userId,@RequestBody AmountRequest request){
        return walletService.withdrawMoney(request.getAmount(), userId);
    }

}
