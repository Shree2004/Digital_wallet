package com.shree.DigitalWallet.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class LoginRequest {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}