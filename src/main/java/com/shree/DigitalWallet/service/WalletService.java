package com.shree.DigitalWallet.service;

import com.shree.DigitalWallet.entity.User;
import com.shree.DigitalWallet.entity.Wallet;
import com.shree.DigitalWallet.repositories.TransactionRepository;
import com.shree.DigitalWallet.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepo;

    @Autowired
    private TransactionRepository transRepo;

    public Wallet getWalletByUserId(Long userId) {
        return walletRepo.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found for userId: " + userId));
    }

    public BigDecimal showBalance(Long userId) {
        Wallet wallet = getWalletByUserId(userId);
        return wallet.getBalanceMoney();
    }

    public BigDecimal depositMoney(BigDecimal money, Long userId){
        if(money == null || money.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("Money can't be in negative");
        }
        Wallet wallet = getWalletByUserId(userId);

        BigDecimal currentBalance = wallet.getBalanceMoney();
        BigDecimal newBalance = currentBalance.add(money);
        wallet.setBalanceMoney(newBalance);
        walletRepo.save(wallet);

        return newBalance;
    }

}
