package com.shree.DigitalWallet.controllers;

import com.shree.DigitalWallet.entity.Transaction;
import com.shree.DigitalWallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{userId}/transactions")
    public List<Transaction> getTransactionsByUserId(@PathVariable Long userId){
        return transactionService.getTransactionsByUserId(userId);
    }

}
