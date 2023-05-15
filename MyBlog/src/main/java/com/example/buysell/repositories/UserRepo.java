package com.example.buysell.repositories;

import com.example.buysell.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    ArrayList<User> findAllByOrderByIdDesc();
}