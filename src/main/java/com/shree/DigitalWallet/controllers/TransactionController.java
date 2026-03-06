package com.shree.DigitalWallet.controllers;

import com.shree.DigitalWallet.entity.Transaction;
import com.shree.DigitalWallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
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
