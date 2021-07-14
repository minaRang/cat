package com.backendStudy.cat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class newController {
    @GetMapping("/new")
    public String mainForm(){
        return "new";
    }
}