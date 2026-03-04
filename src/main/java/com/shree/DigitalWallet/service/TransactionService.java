package com.shree.DigitalWallet.service;

import com.shree.DigitalWallet.entity.Transaction;
import com.shree.DigitalWallet.entity.Wallet;
import com.shree.DigitalWallet.repositories.TransactionRepository;
import com.shree.DigitalWallet.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transRepo;

    @Autowired
    private WalletRepository walletRepo;

    public List<Transaction> getTransactionsByUserId(Long userId){
//
//        Optional<Wallet> userFound = walletRepo.findByUserUserId(userId);
//        Wallet wallet = userFound.get();

        return transRepo.findByWalletUserUserId(userId);
    }

}
