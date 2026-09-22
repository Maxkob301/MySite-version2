package com.example.buysell.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super("Пользователь уже существует: " + username);
    }
}
