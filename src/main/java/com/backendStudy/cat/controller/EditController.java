package com.backendStudy.cat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditController {

    @GetMapping("/edit_requests")
    public String login(){
        return "editRequest";
    }

}
