package com.shree.DigitalWallet.repositories;

import com.shree.DigitalWallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
