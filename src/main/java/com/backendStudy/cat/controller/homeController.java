package com.backendStudy.cat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class homeController {
    @GetMapping("/test")
    public String mainForm(){
        return "login";
    }

}
