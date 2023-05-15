package com.example.buysell.controllers;

import com.example.buysell.models.InfoOfUsers;
import com.example.buysell.models.User;
import com.example.buysell.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class InformationOfUsersController {

    @Autowired
    UserRepo userRepo;
    ArrayList<User> inter = new ArrayList<>();

    @GetMapping("/information")
    public String infoOfUsers(Model model){
        inter = InfoOfUsers.getInfo();
        inter = userRepo.findAllByOrderByIdDesc();
        model.addAttribute("actualInfo", inter);
        return "InformationOfUsers";
    }
}
