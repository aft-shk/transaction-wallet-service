package com.wallet.transaction.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.wallet.transaction.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByClientId(String clientId);
}
