package com.shree.DigitalWallet.DTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
public class TransferRequest {
        private Long senderId;
        private Long receiverId;
        private BigDecimal amount;
    }
