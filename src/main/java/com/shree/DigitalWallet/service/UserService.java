package com.shree.DigitalWallet.service;

import com.shree.DigitalWallet.entity.User;
import com.shree.DigitalWallet.entity.Wallet;
import com.shree.DigitalWallet.repositories.UserRepository;
import com.shree.DigitalWallet.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private WalletRepository walletRepo;

    public User registerUser(User user){

        if (userRepo.findByEmailId(user.getEmailId()).isPresent()){
            throw new RuntimeException("Email already exists");
        }

        if (userRepo.findByMobileNumber(user.getMobileNumber()).isPresent()){
            throw new RuntimeException("Mobile Number already exists");
        }

        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepo.save(user);

        Wallet wallet = new Wallet();
        wallet.setUser(savedUser);
        wallet.setBalanceMoney(BigDecimal.ZERO);

        walletRepo.save(wallet);


        return savedUser;
    }




}
