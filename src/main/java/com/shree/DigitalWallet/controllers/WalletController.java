package com.shree.DigitalWallet.controllers;

import com.shree.DigitalWallet.entity.Wallet;
import com.shree.DigitalWallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/{userId}")
    public Wallet getWalletByUserId(@PathVariable Long userId) {
        return walletService.getWalletByUserId(userId);
    }
}
