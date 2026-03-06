package com.shree.DigitalWallet.controllers;

import com.shree.DigitalWallet.DTO.LoginRequest;
import com.shree.DigitalWallet.DTO.AuthResponse;
import com.shree.DigitalWallet.entity.User;
import com.shree.DigitalWallet.repositories.UserRepository;
import com.shree.DigitalWallet.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        User user = userRepo.findByUserName(request.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(403).body("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getUserName());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}