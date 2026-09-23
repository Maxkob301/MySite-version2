package com.example.buysell.repositories;

import com.example.buysell.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findAllByOrderByIdDesc();
}