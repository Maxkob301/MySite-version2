package com.example.buysell.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFound(NoHandlerFoundException e, Model model) {
        model.addAttribute("status", 404);
        model.addAttribute("error", "Not Found");
        model.addAttribute("message", "Страница не найдена");
        model.addAttribute("path", e.getRequestURL());
        return "error";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String handleUserExists(UserAlreadyExistsException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error/400";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneric(Exception e, Model model) {
        model.addAttribute("error", "Что-то пошло не так");
        return "error/500";
    }
}
