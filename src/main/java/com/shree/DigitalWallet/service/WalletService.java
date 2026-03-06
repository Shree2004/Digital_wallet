package com.shree.DigitalWallet.service;

import com.shree.DigitalWallet.entity.User;
import com.shree.DigitalWallet.entity.Wallet;
import com.shree.DigitalWallet.entity.Transaction;
import com.shree.DigitalWallet.repositories.TransactionRepository;
import com.shree.DigitalWallet.repositories.WalletRepository;
import com.shree.DigitalWallet.repositories.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepo;

    @Autowired
    private TransactionRepository transRepo;

    @Autowired
    private UserRepository userRepo;


    // -------------------------------
    // OLD METHODS (USER ID BASED)
    // -------------------------------

    public Wallet getWalletByUserId(Long userId) {
        return walletRepo.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found for userId: " + userId));
    }

    public BigDecimal showBalance(Long userId) {
        Wallet wallet = getWalletByUserId(userId);
        return wallet.getBalanceMoney();
    }

    @Transactional
    public BigDecimal depositMoney(BigDecimal money, Long userId){

        if(money == null || money.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("Deposit amount must be greater than zero");
        }

        Wallet wallet = getWalletByUserId(userId);

        BigDecimal newBalance = wallet.getBalanceMoney().add(money);
        wallet.setBalanceMoney(newBalance);
        walletRepo.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setType("Deposit");
        transaction.setAmount(money);
        transRepo.save(transaction);

        return newBalance;
    }

    @Transactional
    public BigDecimal withdrawMoney(BigDecimal money, Long userId){

        if(money == null || money.compareTo(BigDecimal.ZERO) <= 0){
            throw new RuntimeException("Withdraw amount must be greater than zero");
        }

        Wallet wallet = getWalletByUserId(userId);

        if(money.compareTo(wallet.getBalanceMoney()) > 0){
            throw new RuntimeException("Insufficient Balance");
        }

        BigDecimal newBalance = wallet.getBalanceMoney().subtract(money);
        wallet.setBalanceMoney(newBalance);
        walletRepo.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setType("Withdraw");
        transaction.setAmount(money);
        transRepo.save(transaction);

        return newBalance;
    }

    @Transactional
    public BigDecimal transferMoney(Long senderId, Long receiverId, BigDecimal amount) {

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Transfer amount must be greater than zero");
        }

        if (senderId.equals(receiverId)) {
            throw new RuntimeException("Cannot transfer money to the same user");
        }

        Wallet senderWallet = getWalletByUserId(senderId);
        Wallet receiverWallet = getWalletByUserId(receiverId);

        if (amount.compareTo(senderWallet.getBalanceMoney()) > 0) {
            throw new RuntimeException("Insufficient balance");
        }

        // sender deduction
        senderWallet.setBalanceMoney(senderWallet.getBalanceMoney().subtract(amount));
        walletRepo.save(senderWallet);

        // receiver addition
        receiverWallet.setBalanceMoney(receiverWallet.getBalanceMoney().add(amount));
        walletRepo.save(receiverWallet);

        // sender transaction
        Transaction senderTransaction = new Transaction();
        senderTransaction.setWallet(senderWallet);
        senderTransaction.setType("TRANSFER_OUT");
        senderTransaction.setAmount(amount);
        transRepo.save(senderTransaction);

        // receiver transaction
        Transaction receiverTransaction = new Transaction();
        receiverTransaction.setWallet(receiverWallet);
        receiverTransaction.setType("TRANSFER_IN");
        receiverTransaction.setAmount(amount);
        transRepo.save(receiverTransaction);

        return senderWallet.getBalanceMoney();
    }



    // -------------------------------
    // NEW METHODS (USERNAME BASED)
    // USED BY JWT CONTROLLER
    // -------------------------------

    private User getUserByUsername(String username){

        return userRepo.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    public Wallet getWalletByUsername(String username){

        User user = getUserByUsername(username);
        return getWalletByUserId(user.getUserId());
    }


    public BigDecimal showBalanceByUsername(String username){

        User user = getUserByUsername(username);
        return showBalance(user.getUserId());
    }


    @Transactional
    public BigDecimal depositMoneyByUsername(String username, BigDecimal amount){

        User user = getUserByUsername(username);
        return depositMoney(amount, user.getUserId());
    }


    @Transactional
    public BigDecimal withdrawMoneyByUsername(String username, BigDecimal amount){

        User user = getUserByUsername(username);
        return withdrawMoney(amount, user.getUserId());
    }


    @Transactional
    public BigDecimal transferMoneyByUsername(String senderUsername,
                                              Long receiverId,
                                              BigDecimal amount){

        User sender = getUserByUsername(senderUsername);
        return transferMoney(sender.getUserId(), receiverId, amount);
    }

}