package com.example.TodoJWT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TodoJWT.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username (used in login & JWT)
    Optional<User> findByUsername(String username);

    // Check if username already exists (used in register validation)
    boolean existsByUsername(String username);
}