package com.shree.DigitalWallet.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.math.BigDecimal;

@Component
@Data
public class AmountRequest {
    private BigDecimal amount;
    public BigDecimal getAmount(){
        return amount;
    }

}
