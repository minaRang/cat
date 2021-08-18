package com.backendStudy.cat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class EditController {

    @GetMapping("/edit_requests")
    public String editRequest(){
        return "editRequest";
    }

}
