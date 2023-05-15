package com.example.buysell.controllers;

import com.example.buysell.models.Greeting;
import com.example.buysell.models.InfoOfUsers;
import com.example.buysell.models.Role;
import com.example.buysell.models.User;
import com.example.buysell.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {


    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value="/greeting", method=RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        model.addAttribute("greeting", greeting);
        authorization(new User(),greeting);
        return "redirect:/greeting";
    }

    private void authorization(User newUser,Greeting greeting){
        newUser.setPassword(greeting.getContent());
        newUser.setUsername(greeting.getUserName());
        InfoOfUsers.getInfo().add(newUser);
        newUser.setDateCreation(greeting.getDate());
        if(greeting.getUserName().equals("Admin12")){
            newUser.setRole(Role.ADMIN);
        }else{
            newUser.setRole(Role.USER);
        }
        newUser.setActive(true);
        userRepo.save(newUser);

    }



}