package com.backendStudy.cat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QnaController {

    @GetMapping("/questions")
    public String login(){
        return "questions";
    }

}
