package com.example.buysell.Services;


import com.example.buysell.exceptions.UserAlreadyExistsException;
import com.example.buysell.models.Role;
import com.example.buysell.models.User;
import com.example.buysell.repositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(User user){
        if(userRepo.findByUsername(user.getUsername()).isPresent()){
            throw new UserAlreadyExistsException(user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setActive(true);
        userRepo.save(user);
    }
}
