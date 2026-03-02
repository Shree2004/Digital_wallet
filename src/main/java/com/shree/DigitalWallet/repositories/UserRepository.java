package com.shree.DigitalWallet.repositories;

import com.shree.DigitalWallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
