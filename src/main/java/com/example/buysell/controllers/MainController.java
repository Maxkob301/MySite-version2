package com.example.buysell.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/index")
    public String greeting(Map<String, Object> model) {
        return "index";
    }



    @GetMapping("/catalog")
    public String catalog(Model model){
        return "catalog";
    }


}