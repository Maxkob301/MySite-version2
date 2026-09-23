package com.example.buysell.controllers;

import com.example.buysell.models.User;
import com.example.buysell.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class InformationOfUsersController {

    private final UserRepo userRepo;

    public InformationOfUsersController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/information")
    public String infoOfUsers(Model model) {
        List<User> users = userRepo.findAllByOrderByIdDesc();
        log.info("Загружено пользователей: {}", users.size());

        model.addAttribute("actualInfo", users);
        return "InformationOfUsers";
    }
}