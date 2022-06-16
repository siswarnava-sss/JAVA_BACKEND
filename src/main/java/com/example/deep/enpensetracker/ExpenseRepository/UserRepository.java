package com.example.deep.enpensetracker.ExpenseRepository;

import com.example.deep.enpensetracker.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
