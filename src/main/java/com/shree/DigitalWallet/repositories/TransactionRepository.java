package com.shree.DigitalWallet.repositories;

import com.shree.DigitalWallet.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
