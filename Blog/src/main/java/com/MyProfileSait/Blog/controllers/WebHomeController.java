package com.MyProfileSait.Blog.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebHomeController {

    @GetMapping("/")
    public String Home( Model model) {
        model.addAttribute("title", "Главная страница");
        return "Home";
    }
}