package com.shree.DigitalWallet.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;

    private BigDecimal balanceMoney;

    @OneToOne
    @JoinColumn(name = "userId", nullable = false, unique = true)
    private User userId;

}
