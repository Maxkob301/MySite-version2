package com.buysell.example;

import com.example.buysell.Services.RegistrationService;
import com.example.buysell.exceptions.UserAlreadyExistsException;
import com.example.buysell.models.User;
import com.example.buysell.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    void shouldRegisterUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");

        when(userRepo.findByUsername("test")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("123456")).thenReturn("hashed");

        registrationService.register(user);

        verify(userRepo).save(user);
        assertEquals("hashed", user.getPassword());
    }

    @Test
    void shouldThrowWhenUserExists() {
        User user = new User();
        user.setUsername("test");

        when(userRepo.findByUsername("test"))
                .thenReturn(Optional.of(user));

        assertThrows(UserAlreadyExistsException.class,
                () -> registrationService.register(user));
    }
}
