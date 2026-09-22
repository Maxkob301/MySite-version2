package com.example.buysell.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("Пользователь не найден: " + username);
    }
}
