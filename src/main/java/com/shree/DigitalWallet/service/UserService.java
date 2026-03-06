package com.shree.DigitalWallet.service;

import com.shree.DigitalWallet.entity.User;
import com.shree.DigitalWallet.entity.Wallet;
import com.shree.DigitalWallet.repositories.UserRepository;
import com.shree.DigitalWallet.repositories.WalletRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private WalletRepository walletRepo;

    public User registerUser(User user) {

        if (userRepo.existsByEmailId(user.getEmailId())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepo.existsByMobileNumber(user.getMobileNumber())) {
            throw new RuntimeException("Mobile number already exists");
        }

        if (userRepo.existsByUserName(user.getUserName())) {
            throw new RuntimeException("Username already exists");
        }

        User savedUser = userRepo.save(user);

        Wallet wallet = new Wallet();
        wallet.setUser(savedUser);
        wallet.setBalanceMoney(BigDecimal.ZERO);
        walletRepo.save(wallet);

        return savedUser;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
}
