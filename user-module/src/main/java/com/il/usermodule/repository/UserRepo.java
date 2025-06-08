package com.il.usermodule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.il.usermodule.model.User;

import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    // Custom query methods can be added here if needed,
    // For example, to find a user by email:
    // Optional<User> findByEmail(String email);
}