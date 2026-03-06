package com.shree.DigitalWallet.repositories;

import com.shree.DigitalWallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);
    Optional<User> findByEmailId(String emailId);
    Optional<User> findByMobileNumber(String mobileNumber);

    boolean existsByEmailId(String emailId);
    boolean existsByMobileNumber(String mobileNumber);
    boolean existsByUserName(String userName);
}