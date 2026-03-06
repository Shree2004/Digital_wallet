package com.shree.DigitalWallet.controllers;

import com.shree.DigitalWallet.DTO.AmountRequest;
import com.shree.DigitalWallet.DTO.TransferRequest;
import com.shree.DigitalWallet.entity.Wallet;
import com.shree.DigitalWallet.service.WalletService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public Wallet getWallet(Authentication authentication) {

        String username = authentication.getName();

        return walletService.getWalletByUsername(username);
    }

    @GetMapping("/balance")
    public BigDecimal showBalance(Authentication authentication) {

        String username = authentication.getName();

        return walletService.showBalanceByUsername(username);
    }

    @PostMapping("/deposit")
    public BigDecimal depositMoney(Authentication authentication,
                                   @RequestBody AmountRequest request) {

        String username = authentication.getName();

        return walletService.depositMoneyByUsername(
                username,
                request.getAmount()
        );
    }

    @PostMapping("/withdraw")
    public BigDecimal withdrawMoney(Authentication authentication,
                                    @RequestBody AmountRequest request) {

        String username = authentication.getName();

        return walletService.withdrawMoneyByUsername(
                username,
                request.getAmount()
        );
    }

    @PostMapping("/transfer")
    public BigDecimal transferMoney(Authentication authentication,
                                    @RequestBody TransferRequest request){

        String senderUsername = authentication.getName();

        return walletService.transferMoneyByUsername(
                senderUsername,
                request.getReceiverId(),
                request.getAmount()
        );
    }
}