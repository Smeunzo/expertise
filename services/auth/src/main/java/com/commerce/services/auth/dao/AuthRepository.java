package com.commerce.services.auth.dao;

import com.commerce.services.auth.entity.AuthCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthCredentials, Long> {
    AuthCredentials findUserCredentialsByUsername(String username);
    boolean existsByUsername(String username);
}
