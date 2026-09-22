package com.example.buysell.controllers;


import com.example.buysell.models.DeletedUser;
import com.example.buysell.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class UserDeleteController {

    private final UserRepo userRepo;

    public UserDeleteController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/delete")
    public String showDeletePage(Model model) {
        model.addAttribute("userDeleted", new DeletedUser());
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteUser(@ModelAttribute DeletedUser deletedUser) {
        if (deletedUser.getUsername() == null || deletedUser.getUsername().isBlank()) {
            log.warn("Имя пользователя не указано");
            return "redirect:/delete";
        }

        userRepo.findByUsername(deletedUser.getUsername())
                .ifPresentOrElse(
                        userRepo::delete,
                        () -> log.warn("Пользователь не найден: {}", deletedUser.getUsername())
                );

        return "redirect:/";
    }
}
