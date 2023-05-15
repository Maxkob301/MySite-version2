package com.example.buysell.controllers;


import com.example.buysell.models.DeletedUser;
import com.example.buysell.models.User;
import com.example.buysell.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UsersOperations {

    @Autowired
    UserRepo userRepo;

    @RequestMapping(value="/delete", method= RequestMethod.POST)
    public String deleteUser(@ModelAttribute DeletedUser deletedUser , Model model){
        model.addAttribute("userDeleted", deletedUser);
        User UserFromDb = userRepo.findByUsername(deletedUser.getUserName());
        if(UserFromDb == null){
            System.out.println("Пользователь не найден");
        }else {
            userRepo.delete(UserFromDb);
        }
        return "redirect:/greeting";
    }

    @RequestMapping(value="/delete", method=RequestMethod.GET)
    public String del(Model model){
        model.addAttribute("userDeleted",new DeletedUser());
        return "delete";
    }

}
